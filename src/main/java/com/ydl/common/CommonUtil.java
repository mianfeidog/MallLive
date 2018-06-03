package com.ydl.common;

import java.io.InputStream;
import java.util.*;

public class CommonUtil {
    /**
     * 获取系统环境配置
     * @return
     */
    public static HashMap<String,String> getMallLiveProperties()
    {
        HashMap<String,String> map = new HashMap<String,String>();
        // 获得资源包
        ResourceBundle rb = ResourceBundle.getBundle("malllive");
        // 通过资源包拿到所有的key
        Enumeration<String> allKey = rb.getKeys();
        // 遍历key 得到 value
        List<String> valList = new ArrayList<String>();
        while (allKey.hasMoreElements())
        {
            String key = allKey.nextElement();
            String value = (String) rb.getString(key);
            map.put(key,value);
        }
        return map;
    }
}
