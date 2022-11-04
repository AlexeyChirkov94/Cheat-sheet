package ru.chirkov.cheat.sheet.aop.udemy.aspect_ordering;

import org.springframework.stereotype.Component;

@Component
public class UniversityLibrary extends AbstractLibrary {

    @Override
    public void getBook(){
        System.out.println("method get book university library");
    }

    public void getMagazine(){
        System.out.println("method get Magazine university library");
    }

    public void returnBook(){
        System.out.println("method return book university library");
    }

    public void returnMagazine(){
        System.out.println("method return Magazine university library");
    }

    public void addBook(){
        System.out.println("method add book university library");
    }

    public void addMagazine(){
        System.out.println("method add Magazine university library");
    }

}
