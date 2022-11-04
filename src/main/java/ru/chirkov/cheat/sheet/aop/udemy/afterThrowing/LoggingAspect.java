package ru.chirkov.cheat.sheet.aop.udemy.afterThrowing;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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
}