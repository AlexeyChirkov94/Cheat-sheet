package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.hardcode.HW_Around;

import org.springframework.stereotype.Component;

@Component
public class MessageWriter {

    public void writeMessage(){
        System.out.print("World");
    }

}
