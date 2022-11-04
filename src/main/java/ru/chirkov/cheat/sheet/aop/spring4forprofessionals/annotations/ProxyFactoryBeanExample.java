package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.annotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProxyFactoryBeanExample {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        MyBean myBean = (MyBean) context.getBean("myBean");

        myBean.execute();
    }
}
