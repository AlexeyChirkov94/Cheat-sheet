package ru.chirkov.cheat.sheet.multithreading.executorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class InvokeAllWithTimeOutExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        List<Task2> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            tasks.add(new Task2("task-" + i, i*1000));
        }

        long beforeInvoke = System.currentTimeMillis();
        List<Future<String>> futureResults = exec.invokeAll(tasks, 3000, TimeUnit.MILLISECONDS); // ждет пока не завершатся все задачи или 3 сек
        long afterInvoke = System.currentTimeMillis();
        System.out.println("invoking time = " + (afterInvoke - beforeInvoke));
        System.out.println("_________");

        long beforeGet = System.currentTimeMillis();
        List<String> readableResults = new ArrayList<>();
        for (Future<String> futureResult : futureResults){
            try {
                readableResults.add(futureResult.get()); //по факту выполняется мнгновенно т.к. все необходимо время ждал метод invokeAll
            } catch (CancellationException e){
                readableResults.add("was CancellationException");
            }
        }
        for (String readableResult : readableResults) System.out.println(readableResult);
        long afterGet = System.currentTimeMillis();
        System.out.println("getting time = " + (afterGet - beforeGet));

        exec.shutdown();
    }


}

class Task2 implements Callable<String>{

    private String field;
    private int wait;

    Task2(String field, int wait){
        this.field = field;
        this.wait = wait;
    }

    @Override
    public String call() throws Exception {
        System.out.println(field + " started, will be wait " + wait);
        Thread.sleep(wait);
        return field + " finish, waiting time = " + wait;
    }
}

/**
 * console output:
 *
 * task-0 started, will be wait 0
 * task-2 started, will be wait 2000
 * task-4 started, will be wait 4000
 * task-1 started, will be wait 1000
 * task-3 started, will be wait 3000
 * invoking time = 3008
 * _________
 * task-0 finish, waiting time = 0
 * task-1 finish, waiting time = 1000
 * task-2 finish, waiting time = 2000
 * was CancellationException
 * was CancellationException
 * getting time = 0
 *
 * Process finished with exit code 0
 */
