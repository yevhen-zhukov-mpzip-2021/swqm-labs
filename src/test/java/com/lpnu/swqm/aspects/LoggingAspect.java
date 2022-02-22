package com.lpnu.swqm.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class LoggingAspect {
    private static final Logger log = LogManager.getLogger(LoggingAspect.class.getName());

    @Pointcut("@annotation(org.testng.annotations.Test)")
    public void callAt() {
    }

    @Around(value = "callAt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        log.info(String.format("The thread ID for %s is %d", method.getName(), Thread.currentThread().getId()));
        return pjp.proceed();
    }
}
