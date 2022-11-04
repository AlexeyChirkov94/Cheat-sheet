package ru.chirkov.cheat.sheet.aop.udemy.before_withPointCutExpression_withParam;

import org.springframework.stereotype.Component;

@Component
public class UniversityLibrary extends AbstractLibrary {

    @Override
    public void getBook(String bookName){
        System.out.println("method get book university library " + bookName);
    }

}
