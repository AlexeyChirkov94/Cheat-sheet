package ru.chirkov.cheat.sheet.aop.udemy.around_exceptions;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UniversityLibrary {

    public List<Integer> getBook(String str){
        System.out.println("method get book university library, str = " + str);

        if (str == "exc"){
            throw new RuntimeException("input is exc");
        }

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        return list;
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
