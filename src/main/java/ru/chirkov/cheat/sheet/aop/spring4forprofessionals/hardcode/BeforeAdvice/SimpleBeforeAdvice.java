package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.hardcode.BeforeAdvice;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import ru.chirkov.cheat.sheet.aop.spring4forprofessionals.hardcode.HW_Around.MessageWriter;

import java.lang.reflect.Method;

public class SimpleBeforeAdvice implements MethodBeforeAdvice {
    public static void main (String[] args) {
        MessageWriter target = new MessageWriter();

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new SimpleBeforeAdvice());
        pf.setTarget(target);
        MessageWriter proxy = (MessageWriter) pf.getProxy();

        proxy.writeMessage();
    }
        @Override
        public void before(Method method, Object[] args, Object target) throws Throwable {
            System.out.println("Before method: " + method.getName());
        }

}
