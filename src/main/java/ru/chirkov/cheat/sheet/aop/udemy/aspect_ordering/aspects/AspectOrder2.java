package ru.chirkov.cheat.sheet.aop.udemy.aspect_ordering.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class AspectOrder2 {

    @Before("ru.chirkov.cheat.sheet.aop.udemy.aspect_ordering.aspects.PointCuts.allGetMethods()")
    public void beforeGetBookAdvice2(){
        System.out.println("beforeGetBookAdvice 2");
    }

}
