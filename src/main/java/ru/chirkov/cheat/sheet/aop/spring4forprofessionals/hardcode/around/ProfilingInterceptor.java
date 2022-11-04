package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.hardcode.around;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

public class ProfilingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(invocation.getMethod().getName());
        Object returnValue = invocation.proceed();
        sw.stop();
        dumpInfo(invocation, sw.getTotalTimeMillis());
        return returnValue;
    }

    private void dumpInfo(MethodInvocation invocation, long ms) {
        Method m = invocation.getMethod();
        Object target = invocation.getThis();
        Object[] args = invocation.getArguments();
        System.out.println("Executed method: " + m.getName()); // выполняемый метод
        System.out.println("On object of type: " + target.getClass().getName()); // класс объекта
        System.out.println("With arguments:"); // аргументы

        for (int х = 0; х < args.length; х++) {
            System.out.print(" > " + args[х]);
            System.out.print("\n");
            System.out.println("Took: " + ms + " ms"); // время выполнения
        }

    }
}
