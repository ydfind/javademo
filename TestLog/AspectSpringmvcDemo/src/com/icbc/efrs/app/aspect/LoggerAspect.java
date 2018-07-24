package com.icbc.efrs.app.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggerAspect {

    private final static Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Pointcut("execution(* com.icbc.efrs.app.controller.*.*(..))")
    public void log() {
    }
    
    public static void logError(String msg) {
    	logger.error(msg);
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("---------------------------------@Before---------------------------");
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attr.getRequest();

        logger.info("url={}; method={}; ip={}; clzmethod={}; args={}",
                request.getRequestURL(),
                request.getMethod(),
                request.getRemoteAddr(),
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
                joinPoint.getArgs()
        );
    }

    @After("log()")
    public void doAfter() {
        System.out.println("-----------------------@After-----------------");
        //doAfter
    }

    @AfterReturning(returning = "ret", pointcut = "log()")
    public void doAfterReturning(Object ret) {
        System.out.println("----------------------@AfterReturning---------------------");
        logger.info("return={}", ret.toString());
    }

}
