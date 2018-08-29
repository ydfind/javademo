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
	// 下载文件的一个示例
	@GetMapping(value = "/down", produces = "text/plain;charset=utf-8")
	@ApiOperation(httpMethod = "GET", value = "请求后端Json", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void getDownData(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-----------------------start--------------------------------");
//		//文件名称
//	    String[] names={"one.jpg","two.jpg","three.jpg","four.jpg"};
//	    //四个文件流
//	    FileInputStream input1 = new FileInputStream(new File("文件路径"));
//	    FileInputStream input2 = new FileInputStream(new File("文件路径"));
//	    FileInputStream input3 = new FileInputStream(new File("文件路径"));
//	    FileInputStream input4 = new FileInputStream(new File("文件路径"));
//	    FileInputStream[] inputs={input1,input2,input3,input4};
	    //ZIP打包图片
		String filename = "D:\\Software Package\\jar\\logback-1.2.3.zip";
	    File zipFile = new File(filename);
	    byte[] buf = new byte[1024];
	    int len;
//	    ZipOutputStream zout=new ZipOutputStream(new FileOutputStream(zipFile));
//	    for (int i = 0; i < inputs.length; i++) { 
//	      FileInputStream in =inputs[i]; 
//	      zout.putNextEntry(new ZipEntry(names[i]));  
//	      while ((len = in.read(buf)) > 0) { 
//	        zout.write(buf, 0, len); 
//	      } 
//	      zout.closeEntry(); 
//	      in.close(); 
//	    }
//	    zout.close();
	    //下载图片
	    FileInputStream zipInput = null;
		try {
			zipInput = new FileInputStream(zipFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    OutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	    response.setContentType("application/octet-stream");
		String returnfile = "";
		int temp = filename.lastIndexOf("/");
		int lastindex = filename.lastIndexOf("\\");
		if(lastindex > temp)
			temp = lastindex;
		returnfile = filename.substring(temp + 1);
	    response.setHeader("Content-Disposition", "attachment; filename=" + returnfile);
	    try {
			while ((len=zipInput.read(buf))!= -1){ 
			  out.write(buf,0,len); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			zipInput.close();
		    out.flush();
		    out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-----------------------end--------------------------------");
	    //删除压缩包
	    //zipFile.delete();
	    
//	    
//	    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
//                headers, HttpStatus.CREATED); 
	}
}
