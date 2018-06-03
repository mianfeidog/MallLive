package com.ydl.framework.listener;

import com.ydl.common.CommonUtil;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;

public class TomcatListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        HashMap<String,String> map= CommonUtil.getMallLiveProperties();
        servletContextEvent.getServletContext().setAttribute("mallLiveMap",map);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
