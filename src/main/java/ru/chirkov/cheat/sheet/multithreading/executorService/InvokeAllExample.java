package ru.chirkov.cheat.sheet.multithreading.executorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InvokeAllExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        List<Task> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++){
           tasks.add(new Task("task-" + i, i*1000));
        }

        long beforeInvoke = System.currentTimeMillis();
        List<Future<String>> results = exec.invokeAll(tasks); // ждет пока не завершатся все задачи
        long afterInvoke = System.currentTimeMillis();
        System.out.println("invoking time = " + (afterInvoke - beforeInvoke));
        System.out.println("_________");

        long beforeGet = System.currentTimeMillis();
        for (Future<String> result : results)
            System.out.println(result.get()); //по факту выполняется мнгновенно т.к. все необходимо время ждал метод invokeAll
        long afterGet = System.currentTimeMillis();
        System.out.println("getting time = " + (afterGet - beforeGet));

        exec.shutdown();
    }


}

class Task implements Callable<String>{

    private String field;
    private int wait;

    Task(String field, int wait){
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
 * task-1 started, will be wait 1000
 * task-0 started, will be wait 0
 * task-4 started, will be wait 4000
 * task-2 started, will be wait 2000
 * task-3 started, will be wait 3000
 * invoking time = 4038
 * _________
 * task-0 finish, waiting time = 0
 * task-1 finish, waiting time = 1000
 * task-2 finish, waiting time = 2000
 * task-3 finish, waiting time = 3000
 * task-4 finish, waiting time = 4000
 * getting time = 0
 *
 * Process finished with exit code 0
 */
