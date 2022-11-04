package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.hardcode.withNameMatchMethodPointcut;

public class NameBean {

    public void foo() {
        System.out.println("foo");
    }

    public void foo(int х) {
        System.out.println("foo " + х);
    }

    public void bar() {
        System.out.println("bar");
    }

    public void yup() {
        System.out.println("yup");
    }
}
