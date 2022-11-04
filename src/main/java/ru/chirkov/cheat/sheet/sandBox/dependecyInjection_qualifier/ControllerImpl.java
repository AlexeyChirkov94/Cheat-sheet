package ru.chirkov.cheat.sheet.sandBox.dependecyInjection_qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ControllerImpl {

    MyService myService;

    @Autowired
    public ControllerImpl(@Qualifier("two") MyService myService) {
        this.myService = myService;
    }

    public void chekInjection(){
        myService.doSomth();
    }

}
