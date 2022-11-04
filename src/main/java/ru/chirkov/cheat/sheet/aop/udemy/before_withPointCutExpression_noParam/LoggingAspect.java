package ru.chirkov.cheat.sheet.aop.udemy.before_withPointCutExpression_noParam;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(public void ru.chirkov.cheat.sheet.aop.udemy.before_withPointCutExpression_noParam.UniversityLibrary.getBook())")
    public void beforeGetBookAdvice(){
        System.out.println("beforeGetBookAdvice");
    }

}
