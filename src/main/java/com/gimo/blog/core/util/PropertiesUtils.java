package com.gimo.blog.core.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 配置文件读取工具类
 * @author GimoXie
 *
 */
public class PropertiesUtils {

	private final static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
	
	private static Properties properties = new Properties();
    
    /**
     * 类的静态块
     * 类加载时读取配置文件信息
     */
    static {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("system-config.properties");
        try {
        	properties.load(in);
        } catch (Exception e) {
        	logger.error("配置文件[system-config.properties]不存在...");
            throw new RuntimeException("this properties file system-config.properties not exists ...");
        }

    }

    /**
     * 获取配置信息
     * @param properties
     * @return
     */
    public static String getProperties(String propertie) {
        String value = properties.getProperty(propertie);
        if (value == null) {
        	logger.error("配置文件[system-config.properties]中不存在["+propertie+"]属性...");
            throw new RuntimeException("this properties system-config.properties key:" + propertie + " not exists ...");
        }
        return value;
    }

    public static void main(String[] args) {
		String value = PropertiesUtils.getProperties("basePath4Win");
		System.out.println(value);
	}
}
