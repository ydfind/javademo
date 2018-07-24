package com.springdemo.aopdeom;
 
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:com/springdemo/aopdeom/aop.xml");
    	 Ticket t = (Ticket)context.getBean("ticket");
    	 t.SaleTicket();
    }
}
