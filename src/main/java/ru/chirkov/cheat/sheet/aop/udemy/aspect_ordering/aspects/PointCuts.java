package ru.chirkov.cheat.sheet.aop.udemy.aspect_ordering.aspects;

import org.aspectj.lang.annotation.Pointcut;


public class PointCuts {

    @Pointcut("execution(public * get*())")
    public void allGetMethods(){}

}
