package com.ydl.common;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;

import java.io.*;
import java.util.HashMap;

public class OssUtil {

    /**
     * 下载文件
     * @param mallLiveMap malllive.properties 参数
     * @param objectName  下载文件
     * @throws IOException
     */
    public static void downloadFile(HashMap<String,String> mallLiveMap,String objectName) throws IOException {
        OSSClient ossClient=OssUtil.getOssClient(mallLiveMap);
        String bucketName = "";
        if(mallLiveMap.containsKey("aliyun.oss.bucketName"))
        {
            bucketName=mallLiveMap.get("aliyun.oss.bucketName");
        }
        //调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及其元信息（meta）。
        OSSObject ossObject = ossClient.getObject("zxhy", objectName);
        //调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();
        if (content != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                System.out.println("\n" + line);
            }
            //数据读取完成后，获取的流一定要显示close，否则会造成资源泄露。
            content.close();
            ossClient.shutdown();
        }
    }

    public static OSSClient getOssClient(HashMap<String,String> mallLiveMap)
    {
        // endpoint以杭州为例，其它region请按实际情况填写。
        String endpoint = "";
        if(mallLiveMap.containsKey("aliyun.oss.endpoint"))
        {
            endpoint=mallLiveMap.get("aliyun.oss.endpoint");
        }
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "";
        if(mallLiveMap.containsKey("aliyun.oss.accessKeyId"))
        {
            accessKeyId=mallLiveMap.get("aliyun.oss.accessKeyId");
        }
        String accessKeySecret ="";
        if(mallLiveMap.containsKey("aliyun.oss.accessKeySecret"))
        {
            accessKeySecret=mallLiveMap.get("aliyun.oss.accessKeySecret");
        }
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        return ossClient;
    }

    /**
     * 上传文件
     * @param mallLiveMap malllive.properties 参数
     * @param content        内容
     * @param objectName    上传文件
     */
    public  static void uploadFile(HashMap<String,String> mallLiveMap,String content,String objectName)
    {
        OSSClient ossClient=OssUtil.getOssClient(mallLiveMap);
        // 使用访问OSS
        //String content = "Hello OSS";
        //ossClient.putObject("<yourBucketName>", "<yourObjectName>", new ByteArrayInputStream(content.getBytes()));
        String bucketName = "";
        if(mallLiveMap.containsKey("aliyun.oss.bucketName"))
        {
            bucketName=mallLiveMap.get("aliyun.oss.bucketName");
        }
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));

        // 关闭ossClient
        ossClient.shutdown();
    }
}
