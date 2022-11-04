package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.annotations;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {

    @Pointcut("execution(* ru.chirkov.projects.aop.annotations.spring4forprofessionals.one..foo*(int)) && args(intValue)")
    public void fooExecution(int intValue) {
    }

    @Pointcut("bean (myDependency*) ")
    public void inМyDependency() {
    }

    @Before("fooExecution(intValue) && inМyDependency()")
    public void simpleBeforeAdvice(JoinPoint joinPoint, int intValue) {
        if (intValue != 100) { // Выполнять, только если intValue не равно 100
            System.out.println("Executing: " +
                    joinPoint.getSignature().getDeclaringTypeName() + " " +
                    joinPoint.getSignature().getName() + " argument: " + intValue);
        }
    }

    @Around("fooExecution(intValue) && inМyDependency()")
    public Object simpleAroundAdvice(ProceedingJoinPoint pjp, int intValue)
            throws Throwable {
// Вывести информацию перед выполнением
        System.out.println("Before execution: " +
                pjp.getSignature().getDeclaringTypeName() + " "
                + pjp.getSignature().getName()
                + " argument: " + intValue);
        Object retVal = pjp.proceed();
// Вывести информацию после вьmолнения
        System.out.println("After execution: " +
                pjp.getSignature().getDeclaringTypeName() + " "
                + pjp.getSignature().getName()
                + " argument: " + intValue);
        return retVal;
    }
}
