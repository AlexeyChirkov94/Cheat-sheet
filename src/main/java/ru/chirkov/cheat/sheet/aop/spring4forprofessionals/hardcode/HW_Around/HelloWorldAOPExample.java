package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.hardcode.HW_Around;

import org.springframework.aop.framework.ProxyFactory;

public class HelloWorldAOPExample {

    public static void main ( String [] args) {
        MessageWriter target = new MessageWriter();

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new MessageDecorator());
        pf.setTarget(target);
        MessageWriter proxy = (MessageWriter) pf.getProxy();

        System.out.print("target object: ");target.writeMessage();
        System.out.println("");
        System.out.print("proxy object: ");proxy.writeMessage();


    }

}
