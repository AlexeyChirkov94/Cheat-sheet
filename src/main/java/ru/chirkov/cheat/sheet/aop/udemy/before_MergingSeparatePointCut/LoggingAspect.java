package ru.chirkov.cheat.sheet.aop.udemy.before_MergingSeparatePointCut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* ru.chirkov.cheat.sheet.aop.udemy.before_MergingSeparatePointCut.UniversityLibrary.get*())")
    private void allGetMethodFromUniversityLibrary(){}

    @Pointcut("execution(* ru.chirkov.cheat.sheet.aop.udemy.before_MergingSeparatePointCut.UniversityLibrary.return*())")
    private void allReturnMethodFromUniversityLibrary(){}

    @Pointcut("allGetMethodFromUniversityLibrary() || allReturnMethodFromUniversityLibrary()")
    private void allGetAndReturnMethodFromUniversityLibrary(){}

    @Before("allGetMethodFromUniversityLibrary()")
    public void beforeGetLoggingAdvice(){
        System.out.println("before Get Logging Advice");
    }

    @Before("allReturnMethodFromUniversityLibrary()")
    public void beforeReturnLoggingAdvice(){
        System.out.println("before Return Logging Advice");
    }

    @Before("allGetAndReturnMethodFromUniversityLibrary()")
    public void beforeGetOrReturnLoggingAdvice(){
        System.out.println("before Get or Return Logging Advice");
    }

}
