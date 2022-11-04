package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("myBean")
public class MyBean {

    private MyDependency dep;

    public void execute(){
        dep.foo(100);
        dep.foo(101);
        dep.bar();
    }

    @Autowired
    public void setDep(MyDependency dep) {
        this.dep = dep;
    }


}
