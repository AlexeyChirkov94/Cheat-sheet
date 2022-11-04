package ru.chirkov.cheat.sheet.aop.udemy.afterReturning;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Test1 {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        UniversityLibrary universityLibrary = context.getBean(UniversityLibrary.class);

        List<Integer> result = universityLibrary.getBook("Harry");

        for (Integer i : result){  //advice add 5 to output
            System.out.println(i);
        }

    }

}
