package com.springdemo.aopdeom;
 
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
 
@Component
public class Ticket {
	
	public void SaleTicket()
	{
		System.out.println("售票");
	}
}
	
	

