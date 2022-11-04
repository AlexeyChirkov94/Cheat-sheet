package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.hardcode.withDynamicMethodMatcherPointcut;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class DynamicPointcutExample {
    public static void main(String[] args) {
        SampleBean target = new SampleBean();

        Pointcut pc = new SimpleDynamicPointcut();
        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pc, advice);

        ProxyFactory pf = new ProxyFactory() ;
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        SampleBean proxy = (SampleBean)pf.getProxy();

        System.out.println("_______");
        proxy.foo(1);
        System.out.println("_______");
        proxy.foo(10);
        System.out.println("_______");
        proxy.foo(100); //here no advice
        System.out.println("_______");

        proxy.bar();
        proxy.bar();
        proxy.bar();
    }
}
