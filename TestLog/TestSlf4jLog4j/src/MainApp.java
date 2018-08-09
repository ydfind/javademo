import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import ch.qos.logback.classic.Logger;
/**
 * @author YDIAN
 *
 */
public class MainApp {
	private static final Logger logger;
	static {
		System.setProperty("log.path", "logs");
		System.setProperty("log.root.level", "trace");
		System.setProperty("log.lowest.level", "trace");
		// DRD: 是否生产环境，也要配置一个变量
//		System.setProperty("app.name", "MainApp");
		logger = LoggerFactory.getLogger(MainApp.class);

	}
	
	public static void main(String[] args) {
		
	
		for(int i = 0; i < 1; i++) {// 1mb的文件能测试出来
			MainApp.logger.trace("it is a trace test");
			MainApp.logger.debug("it is a debug test" + logger.getClass());
			MainApp.logger.error("it is a error test");
			MainApp.logger.info("it is a info test");
			MainApp.logger.warn("it is a warn test");
		}
	}

}
