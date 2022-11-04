package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.annotations;

import org.springframework.stereotype.Component;

@Component("myDependency")
public class MyDependency {

    public void foo(int intValue){
        System.out.println("foo(int): " + intValue);
    }

    public void bar(){
        System.out.println("bar()");
    }
}
