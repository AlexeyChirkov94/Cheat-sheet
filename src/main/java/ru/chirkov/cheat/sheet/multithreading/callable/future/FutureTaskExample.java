package ru.chirkov.cheat.sheet.multithreading.callable.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTaskExample {

    public static void main(String[] args) {
        MyCallable callable1 = new MyCallable(1000);
        MyCallable callable2 = new MyCallable(2000);

        // создаем 2 future таска для 2х callable объектов
        FutureTask<String> futureTask1 = new FutureTask<>(callable1);
        FutureTask<String> futureTask2 = new FutureTask<>(callable2);

        ExecutorService executor = Executors.newFixedThreadPool(2); // екзекьютор с размером пула в 2 потока
        executor.submit(futureTask1);
        executor.submit(futureTask2);

        // выполняем в бесконечном цикле, пока executor service не закончит выполнение всех future тасков
        while (true) {
            try {
                if(futureTask1.isDone() && futureTask2.isDone()){
                    System.out.println("Done"); // заканчиваем работу executor service
                    executor.shutdown();
                    return;
                }

                if(!futureTask1.isDone()){ // ждем, пока future task не закончит выполнение
                    System.out.println("Результат выполнения FutureTask1 = " + futureTask1.get());
                }

                System.out.println("Ждем, пока FutureTask2 не закончит свое выполнение");
                String s = futureTask2.get(400L, TimeUnit.MILLISECONDS);
                if(s != null) System.out.println("Результат выполнения FutureTask2 = " + s);


            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }catch(TimeoutException e){
                System.out.println("Time out!");
            }
        }
    }

    static class MyCallable implements Callable<String> {
        private final long waitTime;
        public MyCallable(int timeInMillis) {
            this.waitTime = timeInMillis;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(waitTime);
            return Thread.currentThread().getName(); // возвращает имя потока, который выполняет этот callable таск
        }

    }

}

/**
 * вывод в консоль:
 *
 * Результат выполнения FutureTask1 = pool-1-thread-1
 * Ждем, пока FutureTask2 не закончит свое выполнение
 * Time out!
 * Ждем, пока FutureTask2 не закончит свое выполнение
 * Time out!
 * Ждем, пока FutureTask2 не закончит свое выполнение
 * Результат выполнения FutureTask2 = pool-1-thread-2
 * Done
 *
 * Process finished with exit code 0
 */
