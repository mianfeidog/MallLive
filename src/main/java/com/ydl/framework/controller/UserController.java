package com.ydl.framework.controller;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ydl.framework.entity.User;
import com.ydl.framework.service.IUserService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ydl
 * @since 2017-09-05
 */
@RestController
@RequestMapping("/framework/user")
public class UserController {
	@Resource
	private IUserService userService;
	
	@RequestMapping(method = { RequestMethod.GET })
    public ResponseEntity<Object> listAll() {
        try {
            List<User> result = userService.selectList(null);
            if(result.size()>0){
                return new ResponseEntity<Object>(result,
                        HttpStatus.OK);
            }else {
                return new ResponseEntity<Object>("NOT FOUND",
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<Object>("ERROR",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
