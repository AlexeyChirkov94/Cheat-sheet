package ru.chirkov.cheat.sheet.multithreading.callable.future;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;

import java.text.SimpleDateFormat;


/**
 * Рассмотрим простейший пример использования интерфейсов Callable и Future. Основная идея данного примера – показать,
 * как можно, используя Future, узнать статус Callable потока и получить возвращенный объект.
 * В примере используется объект executor типа ExecutorService, формирующий пул из трех потоков.
 * Метод submit с параметром Callable возвращает объект Future для каждого из стартуемого потоков.
 */
public class CallableFutureExample {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);  // Определяем пул из трех потоков
        List<Future<String>>  futures = new ArrayList<>(); // Список ассоциированных с Callable задач Future
        Callable<String> callable = new CallableClass(); // Создание экземпляра Callable класса

        for (int i = 0; i < 3; i++) {
            Future<String> future = executor.submit(callable);
            futures.add(future);
        }
        executor.shutdown();

        for (Future<String> future : futures){ //выводим результаты
            try {
                System.out.println(new SimpleDateFormat("HH:mm:ss  ").format(new Date()) + future.get());
            } catch (InterruptedException | ExecutionException e) {}
        }

    }

    static class CallableClass implements Callable<String> { // Класс, реализующий интерфейс Callable
        @Override
        public String call() throws Exception {
            Thread.sleep(2000);
            return Thread.currentThread().getName(); // наименование потока, выполняющего callable задачу
        }
    }

}

/**
 * вывод в консоль:
 *
 * 16:17:54  pool-1-thread-1
 * 16:17:56  pool-1-thread-2
 * 16:17:56  pool-1-thread-3
 */
