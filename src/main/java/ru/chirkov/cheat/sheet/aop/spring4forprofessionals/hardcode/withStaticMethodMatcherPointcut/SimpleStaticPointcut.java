package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.hardcode.withStaticMethodMatcherPointcut;


import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> cls){
        return ("foo".equals(method.getName()));
    }

    @Override
    public ClassFilter getClassFilter(){
        return cls -> (cls == BeanOne.class);
//        return new ClassFilter() {
//            public boolean matches(Class<?> cls) {
//                return (cls == BeanOne.class);
//            }
//        };
    }


}
