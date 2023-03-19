package ru.chirkov.cheat.sheet.multithreading.sandbox;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReasonSyncMapGet {

    private static final int MAX = 10_000_000;
    private final Map<Integer, Integer> instanceMap = new HashMap<>();

    public static void main(String... args) {
        ReasonSyncMapGet main = new ReasonSyncMapGet();

        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + " running");
            Val[] vals = new Val[MAX];
            for (int i = 0; i < MAX; i++) {
                vals[i] = new Val(i, main.getVal(i));
                Val val = new Val(i, main.getVal(i));
                if (val.getValue() != val.getIndex()) System.out.println("here");
            }
            System.out.println(Stream.of(vals).filter(val -> val.getIndex() != val.getValue()).collect(Collectors.toList()));
        };

        Thread thread1 = new Thread(runnable, "Thread 1");
        thread1.start();

        Thread thread2 = new Thread(runnable, "Thread 2");
        thread2.start();
    }

    private int getVal(int key) {
        addIfNotExist(key);
//        synchronized (instanceMap){ // с блоком синхронизации офибка не появлется
            return instanceMap.get(key);
//        }
    }

    private void addIfNotExist(int key) {
        if (!instanceMap.containsKey(key)) {
            synchronized (instanceMap) {
                if (!instanceMap.containsKey(key)) {
                    instanceMap.put(key, key);
                }
            }
        }
    }

}

class Val {

    private final int index;
    private final int value;

    Val(int index, int value){
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }
}

/**
 * Console output: (not every run)
 *
 * Thread 1 running
 * Thread 2 running
 * Exception in thread "Thread 2" java.lang.NullPointerException
 * 	at ru.chirkov.cheat.sheet.multithreading.sandbox.Playground.getVal(Playground.java:34)
 * 	at ru.chirkov.cheat.sheet.multithreading.sandbox.Playground.lambda$main$1(Playground.java:20)
 * 	at java.base/java.lang.Thread.run(Thread.java:834)
 * []
 *
 * Process finished with exit code 0
 */
