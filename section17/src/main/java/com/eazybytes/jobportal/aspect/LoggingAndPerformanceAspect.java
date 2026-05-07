package com.eazybytes.jobportal.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAndPerformanceAspect {

    @Around("""
       execution(* com.eazybytes.jobportal..*.*(..))
    """)
    public Object logAndMeasureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().toShortString();
        Object[] arguments = joinPoint.getArgs();
        log.info("Executing method: {} with arguments: {}", methodName, arguments);
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis();
        log.info("Method Executed successfully: {}. Execution time: {} ms", methodName, executionTime - startTime);
        return result;
    }
}
