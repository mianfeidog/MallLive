package com.ydl.common;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class MsgUtil {
    //产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIjhsZwqru5tIv";
    static final String accessKeySecret = "XSg4q8r0TygmBHVLGrLfTGh0MgqQ1g";

    /**
     * 发送短信测试
     * @return
     * @throws ClientException
     */
    public static SendSmsResponse sendSms() throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,
        // 批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；
        // 发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
        request.setPhoneNumbers("15215856958");
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("zxhy");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_136445098");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,
        // 比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":\"123\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch 请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
        }
        return sendSmsResponse;
    }

    /**
     * 发送短信（含验证码）到多个电话号码
     * @param mallLiveMap   malllive.properties 的环境配置
     * @param telephoneList 电话列表
     * @param identifyCode  验证码
     * @return
     * @throws ClientException
     */
    public static SendSmsResponse sendSms(HashMap<String,String> mallLiveMap,ArrayList<String> telephoneList,String identifyCode) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        String myAccessKeyId= "";
        if(mallLiveMap.containsKey("aliyun.msg.accessKeyId"))
        {
            myAccessKeyId=mallLiveMap.get("aliyun.msg.accessKeyId");
        }
        String myAccessKeySecret="";
        if(mallLiveMap.containsKey("aliyun.msg.accessKeySecret"))
        {
            myAccessKeySecret=mallLiveMap.get("aliyun.msg.accessKeySecret");
        }
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",myAccessKeyId, myAccessKeySecret);

        //IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,
        // 批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；
        // 发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
        String telephoneStr=StringUtils.join(telephoneList,",");
        request.setPhoneNumbers(telephoneStr);
        //必填:短信签名-可在短信控制台中找到
        String signName="";
        if(mallLiveMap.containsKey("aliyun.msg.signName"))
        {
            signName=mallLiveMap.get("aliyun.msg.signName");
        }
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        String templateCode="";
        if(mallLiveMap.containsKey("aliyun.msg.templateCode"))
        {
            templateCode=mallLiveMap.get("aliyun.msg.templateCode");
        }
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,
        // 比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        //request.setTemplateParam("{\"code\":\"123\"}");
        request.setTemplateParam("{\"code\":\""+identifyCode+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch 请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
        }
        return sendSmsResponse;
    }
}
