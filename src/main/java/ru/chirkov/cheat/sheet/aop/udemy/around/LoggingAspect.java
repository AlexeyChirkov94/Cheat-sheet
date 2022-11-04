package ru.chirkov.cheat.sheet.aop.udemy.around;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* get*(..))")
    private void allGetMethods(){}

    @Around("allGetMethods()")
    public Object aroundGetBookAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around before proceed");

        Object result = proceedingJoinPoint.proceed();

        System.out.println("around after proceed");

        return result;
    }


    @Before("allGetMethods()")
    public void beforeGetBookAdvice(){
        System.out.println("beforeGetBookAdvice");
    }

    @AfterReturning(pointcut = "allGetMethods()", returning = "list")
    public void afterReturningGetBookAdvice(List<Integer> list){
        System.out.println("afterReturningGetBookAdvice");

        list.add(5);
    }

    @AfterThrowing(pointcut = "allGetMethods()", throwing = "exc")
    public void afterThrowingGetBookAdvice(Throwable exc){
        System.out.println("afterThrowingGetBookAdvice ex was = " + exc);
    }

    @After("allGetMethods()")
    public void afterGetBookAdvice(){
        System.out.println("after finally");
    }


}