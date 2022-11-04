package ru.chirkov.cheat.sheet.sandBox.dependecyInjection_qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("one")
public class ServiceImplOne implements MyService{

    @Override
    public void doSomth() {
        System.out.println("service one");
    }

}
