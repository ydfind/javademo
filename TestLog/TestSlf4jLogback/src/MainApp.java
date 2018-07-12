import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import ch.qos.logback.classic.Logger;
/**
 * 1.使用logger输出日记
 * 2.能输出到文件；能按一定时间自动保存文件；能过滤某些级别日记；
 * 3.Error能单独到别的文件；
 * 4.文件路径能配置成变量，运行时指定: ${log.path:-log/def/},默认路径log/def/
 * layout与encoder的区别：https://blog.csdn.net/cw_hello1/article/details/51969554
 * logback官方：https://logback.qos.ch/demo.html
 * 配置文件参考：https://www.jianshu.com/p/39178af66aef
 * @author YDIAN
 *
 */
public class MainApp {
	private static final Logger logger;
	static {
		System.setProperty("log.path", "logs");
		System.setProperty("log.root.level", "debug");
		System.setProperty("log.lowest.level", "debug");
		// DRD: 是否生产环境，也要配置一个变量
//		System.setProperty("app.name", "MainApp");
		logger = LoggerFactory.getLogger("MainAppLogger");

	}
	public static void main(String[] args) {
		
//		if (loggerContext != null && loggerContext.getLoggerList().size() > 0) {
//	        //必须清空一下，否则之前加载的logger堆栈信息还保留着StatusPrinter.print会打印出之前的状态
//	        loggerContext.getStatusManager().clear();
//	        loggerContext.reset();
//	        ContextInitializer ci = new ContextInitializer(loggerContext);
//	        try {
//	            ci.autoConfig();
//	        } catch (JoranException e) {
//	            sc.log("-=-=-= Reset Logback status Failed =-=-=- \n" + ExceptionUtil.getStackTrace(e));
//	        }
//	    }
//
//	    WebLogbackConfigurer.initLogging(sc);
//	    StatusPrinter.print(loggerContext);

//		if (org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS) {
//	           System.setProperty("log.path", "${CATALINA_HOME}" + SystemUtils.FILE_SEPARATOR + "logs");
//	        } else {//linux
//	           System.setProperty("log.path", "/logs");
//	        }

//	        if (isProductEnv) {
//	           System.setProperty("log.root.level", "INFO");
//	        } else {//非生产环境
//	           System.setProperty("log.root.level", "DEBUG");
//	        }	
		MainApp.logger.trace("it is a trace test");
		MainApp.logger.debug("it is a debug test" + logger.getClass());
		MainApp.logger.error("it is a error test");
		MainApp.logger.info("it is a info test");
		MainApp.logger.warn("it is a warn test");
	}

}
