import org.apache.log4j.Logger;

public class TestMyLog {
	private static Logger logger = Logger.getLogger("mylog");
	public static void main(String[] args) {
		logger.info("测试是否能够输出到单独的文件：./log/mylog.log");
	}

}
