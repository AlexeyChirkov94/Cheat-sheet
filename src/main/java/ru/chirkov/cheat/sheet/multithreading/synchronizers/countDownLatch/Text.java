package ru.chirkov.cheat.sheet.multithreading.synchronizers.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Text {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(7);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        System.out.println("program is run,    time = " + System.currentTimeMillis()/1000);

        for (int i = 0; i < 7; i++)
            executorService.submit(new Processor(i, countDownLatch));

        executorService.shutdown();
        countDownLatch.await();
        System.out.println("CountDownLatch is open now, time = " + System.currentTimeMillis()/1000);
    }
}

class Processor implements Runnable {

    private int id;
    private CountDownLatch countDownLatch;

    public Processor(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run(){
        try {
            Thread.sleep(3000);
            System.out.println("Thread " + id + " finished, time = " + System.currentTimeMillis()/1000);
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

/**
 * вывод в консоль:
 *
 * program is run,    time = 1668956040
 * Thread 1 finished, time = 1668956043
 * Thread 2 finished, time = 1668956043
 * Thread 0 finished, time = 1668956043
 * Thread 3 finished, time = 1668956046
 * Thread 4 finished, time = 1668956046
 * Thread 5 finished, time = 1668956046
 * Thread 6 finished, time = 1668956049
 * CountDownLatch is open now, time = 1668956049
 *
 * Process finished with exit code 0
 */
