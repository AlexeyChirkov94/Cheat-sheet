package ru.chirkov.cheat.sheet.aop.udemy.aspect_ordering;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test1 {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        UniversityLibrary universityLibrary = context.getBean(UniversityLibrary.class);
        SchoolLibrary schoolLibrary = context.getBean(SchoolLibrary.class);

        universityLibrary.getBook();
        System.out.println("______");
        schoolLibrary.getBook();

    }

}
