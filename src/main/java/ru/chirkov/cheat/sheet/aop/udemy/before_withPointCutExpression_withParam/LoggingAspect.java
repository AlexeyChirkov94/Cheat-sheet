package ru.chirkov.cheat.sheet.aop.udemy.before_withPointCutExpression_withParam;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(public void ru.chirkov.cheat.sheet.aop.udemy.before_withPointCutExpression_withParam.UniversityLibrary.getBook(String))")
    public void beforeGetBookAdvice(){
        System.out.println("beforeGetBookAdvice");
    }

}
