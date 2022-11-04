package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.hardcode.proxyJDKvsCGLIB;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class NoOpBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        //nothing
    }
}
