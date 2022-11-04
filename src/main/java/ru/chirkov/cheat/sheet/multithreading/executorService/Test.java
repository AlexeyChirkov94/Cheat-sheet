package ru.chirkov.cheat.sheet.multithreading.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++)
            executorService.submit(new Work(i)); //передаем 5 работ

        executorService.shutdown(); //перестаем принимать новые задания и отправляем в работу переданные
        System.out.println("All tasks submitted, start execution");

        executorService.awaitTermination(5, TimeUnit.SECONDS); // ждем выполения всех заданий максимум 5 сек
        System.out.println("Main thread stop waiting");
    }
}

class Work implements Runnable{

    private int id;

    public Work(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Work " + id + " was completed, time = " + (System.currentTimeMillis()/1000));
    }

}
