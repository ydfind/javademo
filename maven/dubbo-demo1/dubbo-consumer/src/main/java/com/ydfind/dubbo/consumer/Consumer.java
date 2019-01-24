package com.ydfind.dubbo.consumer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ydfind.dubbo.DemoService;

/**
 * @author Yudi
 */
public class Consumer {
    @SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"META-INF/spring/dubbo-demo-consumer.xml"});
        context.start();
        // Obtaining a remote service proxy
        DemoService demoService = (DemoService)context.getBean("demoService");
        // Executing remote methods
        String hello = demoService.sayHello("world");
        // Display the call result
        System.out.println(hello);
    }
}