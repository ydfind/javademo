package com.ydfind.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Yudi
 */
public class Provider {

    @SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-provider.xml"});
        context.start();
        System.out.println("Provider started.");
        System.in.read(); // press any key to exit，让提供方一直运行
    }
}