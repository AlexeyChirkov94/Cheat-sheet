package ru.chirkov.cheat.sheet.aop.udemy.before_withPointCutExpression_noParam;

import org.springframework.stereotype.Component;

@Component
public class UniversityLibrary extends AbstractLibrary {

    @Override
    public void getBook(){
        System.out.println("method get book university library");
    }

}
