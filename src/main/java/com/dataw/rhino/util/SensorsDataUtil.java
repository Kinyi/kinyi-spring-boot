package com.dataw.rhino.util;

import com.sensorsdata.analytics.javasdk.SensorsAnalytics;

import java.io.IOException;

/**
 * @author Kinyi_Chan
 * @since 2021-04-12
 */
public class SensorsDataUtil {

    private static volatile SensorsAnalytics instance;

//    private SensorsDataUtil() {}

    /**
     * 获取神策单例对象sa
     *
     * @return
     */
    public static SensorsAnalytics getInstance() {
        try {
            if (instance == null) {
                synchronized (SensorsDataUtil.class) {
                    if (instance == null) {
                        instance = new SensorsAnalytics(new SensorsAnalytics.ConcurrentLoggingConsumer("您的日志文件路径"));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
