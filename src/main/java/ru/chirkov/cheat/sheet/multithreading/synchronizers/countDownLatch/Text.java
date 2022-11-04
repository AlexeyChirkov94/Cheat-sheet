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
