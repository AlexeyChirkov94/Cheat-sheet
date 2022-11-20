package ru.chirkov.cheat.sheet.multithreading.common.interruption;

import java.util.Random;

public class OneMoreExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread( ()-> {
            Random random = new Random();
            for (int i = 0; i < 1_000_000_000; i++){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("was interapted, timme: " + System.currentTimeMillis()/1000);
                }
                Math.sin(random.nextDouble());
            }
        });

        System.out.println("start,          timme: " + System.currentTimeMillis()/1000);
        thread.start();

        Thread.sleep(2000);
        thread.interrupt();
        Thread.sleep(1000);
        thread.interrupt();
        Thread.sleep(1000);
        thread.interrupt();

        thread.join();
        System.out.println("finished, timme: " + System.currentTimeMillis()/1000);


    }
}

/**
 * вывод в консоль:
 *
 * start,          timme: 1668956694
 * was interapted, timme: 1668956696
 * was interapted, timme: 1668956697
 * was interapted, timme: 1668956698
 * ... proceed working
 */
