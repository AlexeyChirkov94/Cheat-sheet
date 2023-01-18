package ru.chirkov.cheat.sheet.multithreading.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Пример показывает что изменения внесенные через обертку AtomicReference видны сразу всем потокам,
 * а в обычную перемнную нет. Даже не смотря на то что она volatile
 */

public class AtomicReferenceExample {
    private static volatile String message = "initial value of simple string and atomic ref by string";
    private static AtomicReference<String> atomicReference;

    public static void main(final String[] arguments) throws InterruptedException {
        atomicReference = new AtomicReference<>(message);

        Thread thread = new Thread(() -> {
            atomicReference.compareAndSet(message, "new value of atomic ref on string");
//            atomicReference.set("new value of atomic ref on string"); //так-же сработает
            message = "new value of string";
        });

        thread.start();
//        thread.join(); //если раскоментить значение строки тоже обновиться (даже без volatile)

        System.out.println("Message is: " + message);
        System.out.println("Atomic Reference of Message is: " + atomicReference.get());
    }

}

/**
 * console output:
 *
 * Message is: initial value of simple string and atomic ref by string
 * Atomic Reference of Message is: new value of atomic ref on string
 */
