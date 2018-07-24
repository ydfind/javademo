package com.springdemo.aopdeom;
 
import java.util.Date;
 
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
 
@Component
@Order(1)
@Aspect
public class TicketAspect {
 
	@Pointcut("execution(* com.springdemo.aopdeom.*.*(..))")
	public void TicketMethod()
	{
		
	}
	
	 @Before(value = "execution(* com.springdemo.aopdeom.*.*(..))")
	    public void doBefore(JoinPoint jp) {
	        System.out.println("执行前准备,方法名称"+jp.getSignature().getName()+",时间:"+new Date());
                
	    }
	 
	    @AfterReturning(value = "TicketMethod()")
	    public void doAfter(JoinPoint jp) {
	
	        System.out.println("执行后,时间:"+new Date());
	    }
	    
 
	    @Around(value = "execution(* com.springdemo.aopdeom.*.*(..))")
	    public void doAround(ProceedingJoinPoint pjp) throws Throwable {
	        System.out.println("执行前环绕\n");
	        Object result = pjp.proceed();
	        System.out.println("执行后环绕\n");
	    }
	    
	
	    @AfterThrowing(value = "execution(* com.springdemo.aopdeom.*.*(..))", throwing = "e")
	    public void doThrow(JoinPoint jp, Throwable e) {
	        System.out.println("出现异常");
	    }
 
 
}
