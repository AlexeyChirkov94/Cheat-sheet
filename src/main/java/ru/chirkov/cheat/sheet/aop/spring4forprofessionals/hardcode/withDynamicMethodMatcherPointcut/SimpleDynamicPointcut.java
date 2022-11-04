package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.hardcode.withDynamicMethodMatcherPointcut;


import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> cls){
        // Выполнить статическую проверку
        System.out.println("Static check for " + method.getName());
        return ("foo".equals(method.getName()));
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        // Выполнить динамическую проверку
        System. out. println ( "Dynamic check for " + method.getName()) ;
        int х = ((Integer) args[0]) .intValue();
        return (х != 100);
    }

    @Override
    public ClassFilter getClassFilter(){
        return cls -> (cls == SampleBean.class);
//        return new ClassFilter() {
//            public boolean matches(Class<?> cls) {
//                return (cls == SampleBean.class);
//            }
//        };
    }


}
