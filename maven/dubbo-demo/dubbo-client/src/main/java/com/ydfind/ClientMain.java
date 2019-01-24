package com.ydfind;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ydfind.service.ElasticService;

public class ClientMain {
	private static Logger logger = LoggerFactory.getLogger(ClientMain.class);

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "dubbo.xml" });
		try {
			context.start();
			ElasticService service = (ElasticService) context.getBean("elasticService");
			logger.info(service.helloDubbo("world"));
			context.close();
			logger.info("客户端正确结束了");
		} catch (Exception e) {
			logger.info("客户端 报错了");
			e.printStackTrace();
		}
	}
}
