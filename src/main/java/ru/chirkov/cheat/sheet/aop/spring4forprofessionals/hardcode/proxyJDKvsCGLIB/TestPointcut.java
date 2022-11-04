package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.hardcode.proxyJDKvsCGLIB;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class TestPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class cls) {
        return ( "advised". equals (method .getName ()));
    }
}
