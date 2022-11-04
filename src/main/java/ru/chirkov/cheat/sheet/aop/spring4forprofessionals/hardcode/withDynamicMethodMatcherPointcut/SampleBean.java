package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.hardcode.withDynamicMethodMatcherPointcut;

public class SampleBean {

    public void foo(int x) {
        System.out.println("Invoked foo with x = " + x);
    }

    public void bar(){
        System.out.println("Invoked bar");
    }

}
