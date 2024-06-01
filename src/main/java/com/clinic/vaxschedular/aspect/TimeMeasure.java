package com.clinic.vaxschedular.aspect;

//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(0)
@Component
public class TimeMeasure {

    @Pointcut("execution(* com.clinic.vaxschedular.Controller.AdminController.*(..))")
    public void AdminControllerMethods() {}


//    @Pointcut("execution(* com.clinic.vaxschedular.Controller.AuthenticationController.*(..))")
//    public void AuthControllerMethods() {}
//    || AuthControllerMethods()


    @Pointcut("execution(* com.clinic.vaxschedular.Controller.PatientController.*(..))")
    public void PatientControllerMethods() {}


    @Around("AdminControllerMethods() || PatientControllerMethods()")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        StringBuilder message = new StringBuilder();
        message.append("\nExecution Time: ").append(System.currentTimeMillis()  - startTime).append(" milliseconds\n");
        System.out.println(message.toString());

        return result;
    }
}