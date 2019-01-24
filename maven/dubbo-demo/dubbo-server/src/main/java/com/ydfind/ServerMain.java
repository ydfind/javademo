package com.ydfind;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;
/**
 * 
 * @author Yudi
 *
 */
public class ServerMain {
	private static Logger logger = LoggerFactory.getLogger(ServerMain.class);
    public static void main(String[] args) throws IOException {
    	logger.info("--------------------start------------------------");
    	try {
 
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext( new String[] { "dubbo.xml" });
        context.start();
        logger.info("任意按键退出");
        System.in.read();
        context.close();
        logger.info("服务器正常关闭-----------------------------");
    	}catch(Exception e) {
    		logger.info("服务器报错了-------------------");
    		e.printStackTrace();
    	}
    }
}
