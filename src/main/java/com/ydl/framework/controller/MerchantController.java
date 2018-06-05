package com.ydl.framework.controller;


import com.aliyuncs.exceptions.ClientException;
import com.ydl.common.MsgUtil;
import com.ydl.common.OssUtil;
import com.ydl.framework.entity.User;
import com.ydl.framework.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ydl
 * @since 2017-09-05
 */
@RestController
@RequestMapping("/merchant")
public class MerchantController {
	//@Resource
	//private IUserService userService;

    @ResponseBody
	@RequestMapping(value="/getall",method = { RequestMethod.GET })
    public ResponseEntity<Object> listAll() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        HashMap<String, String> mallLiveMap = (HashMap<String, String>) servletContext.getAttribute("mallLiveMap");

        //发送短信
//	    try{
//            ArrayList<String> telephoneList = new ArrayList<String>();
//            telephoneList.add("15215856958");
//            MsgUtil.sendSms(mallLiveMap,telephoneList,"test");
//            //MsgUtil.sendSms();
//        }catch (ClientException e){
//            e.printStackTrace();
//        }

        //上传文件
        OssUtil.uploadFile(mallLiveMap,"hello,world","malllive/testfile/test.txt");

        //读取文件
        try {
            OssUtil.downloadFile(mallLiveMap,"malllive/testfile/test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Object>("",HttpStatus.OK);






    }
}
