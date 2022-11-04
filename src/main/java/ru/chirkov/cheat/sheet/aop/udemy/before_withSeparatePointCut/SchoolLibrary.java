package ru.chirkov.cheat.sheet.aop.udemy.before_withSeparatePointCut;

import org.springframework.stereotype.Component;

@Component
public class SchoolLibrary  extends AbstractLibrary {

    @Override
    public void getBook() {
        System.out.println("method get book school library");
    }

}
