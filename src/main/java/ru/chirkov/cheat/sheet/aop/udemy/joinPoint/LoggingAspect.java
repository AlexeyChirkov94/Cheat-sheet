package ru.chirkov.cheat.sheet.aop.udemy.joinPoint;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* get*(..))")
    private void allGetMethods(){}


    @Before("allGetMethods()")
    public void beforeGetBookAdvice(JoinPoint joinPoint){
        System.out.println("beforeGetBookAdvice");
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        System.out.println("methodSignature = " + methodSignature);
        System.out.println("return Type = " + methodSignature.getReturnType());
        System.out.println("Method = " + methodSignature.getMethod());
        System.out.println("Method name= " + methodSignature.getMethod().getName());

        Object[] args = joinPoint.getArgs();
        System.out.println("Args = ");
        for (Object arg : args){
            System.out.println(arg);
        }


    }

}