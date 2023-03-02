package ru.chirkov.cheat.sheet.multithreading.executorService.MyOwnThreadAndFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {

    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();

    public MyThread(Runnable runnable, String name) {
        super(runnable, name + ", thread-" + created.incrementAndGet());
    }

    @Override
    public void run() {
        System.out.println("created " + getName());
        try {
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
            System.out.println("Exiting "+ getName());
        }
    }

    public static int getThreadsCreated() {
        return created.get();
    }

    public static int getThreadsAlive() {
        return alive.get();
    }
}
