package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.hardcode.withNameMatchMethodPointcut;


import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class NamePointcutExample {

    public static void main(String[] args) {
        NameBean target = new NameBean();

        Advice advice = new SimpleAdvice();
        NameMatchMethodPointcut pc = new NameMatchMethodPointcut();
        pc.addMethodName("foo") ;
        pc.addMethodName("bar");
        Advisor advisor = new DefaultPointcutAdvisor(pc, advice);

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        NameBean proxy = (NameBean)pf.getProxy();

        System.out.println("__________");
        proxy.foo();
        System.out.println("__________");
        proxy.foo(999);
        System.out.println("__________");
        proxy.bar();
        System.out.println("__________");
        proxy.yup();
        System.out.println("__________");
    }

}
