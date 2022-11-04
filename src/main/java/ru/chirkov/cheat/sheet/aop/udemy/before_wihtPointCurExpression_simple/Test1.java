package ru.chirkov.cheat.sheet.aop.udemy.before_wihtPointCurExpression_simple;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test1 {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        Library library = context.getBean(Library.class);

        library.getBook();

    }

}
