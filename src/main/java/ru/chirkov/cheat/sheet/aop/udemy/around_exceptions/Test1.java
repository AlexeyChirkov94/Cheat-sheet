package ru.chirkov.cheat.sheet.aop.udemy.around_exceptions;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test1 {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        UniversityLibrary universityLibrary = context.getBean(UniversityLibrary.class);

//        List<Integer> result = universityLibrary.getBook("Harry");
//        for (Integer i : result){
//            System.out.println(i);
//        }

//        System.out.println("______________");
//        try{
            universityLibrary.getBook("exc");
//        } catch (RuntimeException ex){
//            System.out.println("I`m catch " + ex);
//        }


    }

}
