package ru.chirkov.cheat.sheet.sandBox.dependecyInjection_qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("two")
public class ServiceImplTwo implements MyService{

    public void doSomth() {
        System.out.println("service two");
    }

}
