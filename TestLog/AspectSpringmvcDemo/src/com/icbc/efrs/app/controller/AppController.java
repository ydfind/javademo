package com.icbc.efrs.app.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags="移动端api服务")
public class AppController {	
	private static final Logger logger;
	static {
		System.setProperty("log.path", "logs");
		System.setProperty("log.root.level", "trace");
		System.setProperty("log.lowest.level", "trace");
		// DRD: 是否生产环境，也要配置一个变量
	//	System.setProperty("app.name", "MainApp");
		logger = LoggerFactory.getLogger("MainAppLogger");
	
	}

	/**
	 * 根据name上下文根，获取结果json
	 * 
	 * @param paramStr
	 * @return
	 * @throws IOException
	 */
	@GetMapping(value = "/test", produces = "text/plain;charset=utf-8")
	@ApiOperation(httpMethod = "GET", value = "请求后端Json", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getJson(HttpServletRequest request) {
		
		System.out.println("-------------------logger class = " + logger.getClass());
//		AppController.logger.trace("it is a trace test");
//		AppController.logger.debug("it is a debug test" + logger.getClass());
//		AppController.logger.error("it is a error test");
//		AppController.logger.info("it is a info test");
//		AppController.logger.warn("it is a warn test");
		System.out.println("--------------------------");
		return "test";
	}
}
