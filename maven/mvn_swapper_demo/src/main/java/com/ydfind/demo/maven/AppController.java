package com.ydfind.demo.maven;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//  http://localhost:8083/mvn_swapper_demo/app/json/jsonpost
@RestController
@RequestMapping("/app")
@Api(tags="这是一个测试AppController")
public class AppController {
	/**
	 * 根据name上下文根，获取结果json
	 * 
	 * @param paramStr
	 * @return
	 * @throws IOException
	 */
	@GetMapping(value = "/json/jsonpost", produces = "applicatoin/json;charset=utf-8")
//	@ApiOperation(httpMethod = "Get", value = "前端请求后端的测试json", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getServerJson(HttpServletRequest request) {
		// TODO 从request解析json，调用相应接口服务进行json处理，调用字段前置服务，调用翻译服务
		String result = "返回测试结果";
		// body解析出请求类型，顺便校验格式
		return result;
	}
}
