package ru.chirkov.cheat.sheet.multithreading.executorService.MyOwnThreadAndFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Пример наглядно демострирует настройки конструктора ThreadPoolExecutor
 * Сколько в пуле потоков при старте, при простое, все данные каждый цикл выводятся в консоль
 * Играй =)
 */
public class WithMain {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = new ThreadPoolExecutor(2, 40,
                0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(),
                new MyThreadFactory("my pool"),
                new ThreadPoolExecutor.AbortPolicy()); //AbortPolicy - default

        for (int i = 1; i < 20; i++) {
            System.out.print("created threads = " + MyThread.getThreadsCreated());
            System.out.println(", alive threads = " + MyThread.getThreadsAlive());
            exec.submit(new Task(i, 1000));
            Thread.sleep(100L);
            if (i == 10) {
                System.out.println("\t SLEEP 10s!!");
                Thread.sleep(10_000L);
                System.out.println("\t WAKE UP! \n");
            }
        }

        exec.shutdown();
    }

}


