package ru.chirkov.cheat.sheet.reactive;

import ru.chirkov.cheat.sheet.reactive.spec.Publisher;

public class App {

    public static void main(String[] args) {
        var result1 = Publisher.just(1,2,3)
                .peek(System.out::print)
                .map(i-> i+1)
                .peek(System.out::println)
                .collect();
        System.out.println(result1);
    }

}
