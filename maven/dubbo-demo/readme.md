# dubbo+zookeeper的demo
eclipse导入两个工程，运行zookeeper，分别执行ServerMain.java和ClientMain.java，工程即成功

# 项目详细
### 1.环境
jdk、maven等工具已安装。下面的例子基于eclipse，绿色版的zookeeper，win10系统。

### 2.运行zookeeper
运行安装目录下“./bin/zkServer.cmd”，以启动zookper。
“./conf/zoo.cfg”配置文件可以配置一些属性，如下：
    dataDir=D:\ProgramData\zookeeper
dataLogDir=D:\ProgramData\zookeeper\log
### 3.创建maven工程“dubbo-server”
目录结构和最终jar包，如下：

**1）ServerMain.java**

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

**2）ElasticService.java**

    package com.ydfind.service;
    /**
     *
     * @author Yudi
     *
     */
    public interface ElasticService {
        String helloDubbo(String msg);
    }

**3）ElasticServiceImpl.java**

    package com.ydfind.service.impl;

    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import com.ydfind.service.ElasticService;
    /**
     *
     * @author Yudi
     *
     */
    public class ElasticServiceImpl implements ElasticService {

        private static final Logger logger = LoggerFactory.getLogger(ElasticServiceImpl.class);

        public String helloDubbo(String msg) {
        	logger.info("服务器收到:" + msg);
            return "hi!" + msg;
        }
    }

**4）Dubbo.xml**

    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
                               http://www.springframework.org/schema/beans/spring-beans.xsd
                               http://code.alibabatech.com/schema/dubbo
                               http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

        <dubbo:application name="dubbo-server" owner="ydfind" />
        <!-- zookeeper注册中心 -->
        <dubbo:registry address="zookeeper://127.0.0.1:2181" />
        <dubbo:protocol contextpath="dubbo" port="20881" />
        <dubbo:monitor protocol="registry"/>

        <!-- 服务具体实现 -->
        <bean id="elasticService" class="com.ydfind.service.impl.ElasticServiceImpl" />

        <!-- 向注册中心注册暴漏服务地址,注册服务 -->
        <dubbo:service interface="com.ydfind.service.ElasticService" ref="elasticService" protocol="dubbo" timeout="50000"/>
    </beans>

**5）log4j.xml**

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE log4j:configuration SYSTEM "./log4j.dtd">
    <log4j:configuration>
    	<appender name="consoleAppender" class="org.apache.log4j.ConsoleAppender">
    		<layout class="org.apache.log4j.PatternLayout">
    			<param name="ConversionPattern" value="[%-d{yyyy-MM-dd HH:mm:ss.SSS}][%p][%t] %X{operationName} %X{userId} - %m%n" />
    		</layout>
    	</appender>

    	<root>
    		<level value="INFO" />
    		<appender-ref ref="consoleAppender" />
    	</root>
    </log4j:configuration>

**6）pom.xml**

    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    	<modelVersion>4.0.0</modelVersion>
    	<groupId>com.ydfind</groupId>
    	<artifactId>dubbo-server</artifactId>
    	<version>0.0.1-SNAPSHOT</version>

    	<properties>
    		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    	</properties>

    	<dependencies>
    		<!-- spring begin -->
    		<dependency>
    			<groupId>org.springframework</groupId>
    			<artifactId>spring-context</artifactId>
    			<version>4.1.6.RELEASE</version>
    		</dependency>
    		<!-- spring end -->
    		<!-- dubbo begin -->
    		<dependency>
    			<groupId>com.alibaba</groupId>
    			<artifactId>dubbo</artifactId>
    			<version>2.5.3</version>
    		</dependency>
    		<!-- dubbo end -->
    		<!-- 注册中心zookeeper begin -->
    		<dependency>
    			<groupId>org.apache.zookeeper</groupId>
    			<artifactId>zookeeper</artifactId>
    			<version>3.3.6</version>
    		</dependency>
    		<!-- 注册中心zookeeper end -->
    		<!-- log begin -->
    		<dependency>
    			<groupId>commons-logging</groupId>
    			<artifactId>commons-logging</artifactId>
    			<version>1.1.1</version>
    		</dependency>
    		<dependency>
    			<groupId>log4j</groupId>
    			<artifactId>log4j</artifactId>
    			<version>1.2.16</version>
    			<exclusions>
    				<exclusion>
    					<groupId>com.sun.jdmk</groupId>
    					<artifactId>jmxtools</artifactId>
    				</exclusion>
    				<exclusion>
    					<groupId>com.sun.jmx</groupId>
    					<artifactId>jmxri</artifactId>
    				</exclusion>
    				<exclusion>
    					<artifactId>jms</artifactId>
    					<groupId>javax.jms</groupId>
    				</exclusion>
    				<exclusion>
    					<artifactId>mail</artifactId>
    					<groupId>javax.mail</groupId>
    				</exclusion>
    			</exclusions>
    		</dependency>
    		<!-- log end -->
    		<!-- other begin -->
    		<dependency>
    			<groupId>org.jboss.netty</groupId>
    			<artifactId>netty</artifactId>
    			<version>3.2.0.Final</version>
    		</dependency>
    		<dependency>
    			<groupId>com.101tec</groupId>
    			<artifactId>zkclient</artifactId>
    			<version>0.8</version>
    		</dependency>
    		<!-- other end -->
    	</dependencies>
    </project>

### 4.maven的jar工程“dubbo-client”

**注意**
需要把dubbo-server的包加入

**1）ClientMain.java**

    package com.ydfind;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.context.support.ClassPathXmlApplicationContext;
    import com.ydfind.service.ElasticService;
    /**
     *
     * @author Yudi
     *
     */
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

**2）dubbo.xml**

    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
                               http://www.springframework.org/schema/beans/spring-beans.xsd
                               http://code.alibabatech.com/schema/dubbo
                               http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

        <dubbo:application name="dubbo-consumer" />

        <dubbo:registry address="zookeeper://127.0.0.1:2181" />

        <!-- 向注册中心订阅服务 -->
        <dubbo:reference id="elasticService" interface="com.ydfind.service.ElasticService" />

    </beans>

**3）log4j.xml**

同上

**4）pom.xml**

    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    	<modelVersion>4.0.0</modelVersion>
    	<groupId>com.ydfind</groupId>
    	<artifactId>dubbo-client</artifactId>
    	<version>0.0.1-SNAPSHOT</version>

    	<properties>
    		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    	</properties>

    	<dependencies>
    		<!-- spring begin -->
    		<dependency>
    			<groupId>org.springframework</groupId>
    			<artifactId>spring-context</artifactId>
    			<version>4.1.6.RELEASE</version>
    		</dependency>
    		<!-- spring end -->

    		<!-- dubbo begin -->
    		<dependency>
    			<groupId>com.alibaba</groupId>
    			<artifactId>dubbo</artifactId>
    			<version>2.5.3</version>
    		</dependency>
    		<!-- dubbo end -->

    		<!-- 注册中心zookeeper begin -->
    		<dependency>
    			<groupId>org.apache.zookeeper</groupId>
    			<artifactId>zookeeper</artifactId>
    			<version>3.3.6</version>
    		</dependency>
    		<!-- 注册中心zookeeper end -->

    		<!-- log begin -->
    		<dependency>
    			<groupId>log4j</groupId>
    			<artifactId>log4j</artifactId>
    			<version>1.2.16</version>
    			<exclusions>
    				<exclusion>
    					<groupId>com.sun.jdmk</groupId>
    					<artifactId>jmxtools</artifactId>
    				</exclusion>
    				<exclusion>
    					<groupId>com.sun.jmx</groupId>
    					<artifactId>jmxri</artifactId>
    				</exclusion>
    				<exclusion>
    					<artifactId>jms</artifactId>
    					<groupId>javax.jms</groupId>
    				</exclusion>
    				<exclusion>
    					<artifactId>mail</artifactId>
    					<groupId>javax.mail</groupId>
    				</exclusion>
    			</exclusions>
    		</dependency>
    		<!-- log end -->

    		<!-- other begin -->
    		<dependency>
    			<groupId>com.101tec</groupId>
    			<artifactId>zkclient</artifactId>
    			<version>0.8</version>
    		</dependency>
    		<dependency>
    			<groupId>com.ydfind</groupId>
    			<artifactId>dubbo-server</artifactId>
    			<version>0.0.1-SNAPSHOT</version>
    		</dependency>
    		<!-- other end -->
    	</dependencies>
    </project>

### 5.运行测试
* 1）运行zookeeper；
* 2）运行dubbo-server的ServerMain.java；
* 3）运行dubbo-client的ClientMain.java；
* 4）将输出：

注意：zookeeper和dubbo-server不能关闭，否则dubbo-client无法运行成功；
