package ru.chirkov.cheat.sheet.aop.udemy.aspect_ordering;

import org.springframework.stereotype.Component;

@Component
public class SchoolLibrary  extends AbstractLibrary {

    @Override
    public void getBook(){
        System.out.println("method get book School library");
    }

    public void getMagazine(){
        System.out.println("method get Magazine School library");
    }

    public void returnBook(){
        System.out.println("method return book School library");
    }

    public void returnMagazine(){
        System.out.println("method return Magazine School library");
    }

    public void addBook(){
        System.out.println("method add book School library");
    }

    public void addMagazine(){
        System.out.println("method add Magazine School library");
    }

}
