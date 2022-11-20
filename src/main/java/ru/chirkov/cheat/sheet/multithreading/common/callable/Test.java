package ru.chirkov.cheat.sheet.multithreading.common.callable;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(()->{
            Random random = new Random();
            int result = random.nextInt(10);
            System.out.println("number is " + result);
            if (result < 5){
                System.out.println("something went wrong");
                throw new RuntimeException("bad news");
            }
            return result;
        });
        executorService.shutdown();

        int result = 0 ;
        try {
            result = future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            System.out.println("exception was caught at main thread");
            Throwable exc = e.getCause();
            System.out.println("exception message = " + exc.getMessage());
        }

        System.out.println("result is = " + result);
    }
}

/**
 * вывод в консоль:
 *
 * number is 6
 * result is = 6
 *
 * Process finished with exit code 0
 *
 * ____________OR____________
 *
 * number is 4
 * something went wrong
 * exception was caught at main thread
 * exception message = bad news
 * result is = 0
 *
 * Process finished with exit code 0
 */
