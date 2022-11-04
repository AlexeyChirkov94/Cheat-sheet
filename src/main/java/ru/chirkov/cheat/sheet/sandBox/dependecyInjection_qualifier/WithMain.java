package ru.chirkov.cheat.sheet.sandBox.dependecyInjection_qualifier;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class WithMain {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ru.chirkov.projects.dependecyInjection");

        ControllerImpl controller = context.getBean(ControllerImpl.class);

        controller.chekInjection();



    }


}
