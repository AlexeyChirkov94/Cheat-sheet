package ru.chirkov.cheat.sheet.multithreading.callable.future;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
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
 *
 * Позже в пример были добавлены примеры прервывания переданой потребителю задачи и вывоз исключения внутри выполняемой задачи.
 * Обратите внимание размер пула потоков не имзенился в обоих сценариях.
 */
public class CallableFutureExample {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);  // Определяем пул из трех потоков
        List<Future<String>>  futures = new ArrayList<>(); // Список ассоциированных с Callable задач Future

        for (int i = 0; i < 10; i++) {
            Callable<String> callable = new CallableClass(i); // Создание экземпляра Callable класса
            Future<String> future = executor.submit(callable);
            if (i == 3) future.cancel(true); // можно прирвать и запущеную задачу (замени 3 на 1)
            futures.add(future);
        }
        executor.shutdown();

        for (Future<String> future : futures){ //выводим результаты
            try {
                System.out.println(new SimpleDateFormat("HH:mm:ss  ").format(new Date()) + future.get());
            } catch (InterruptedException | ExecutionException | CancellationException e) {
                System.out.println("something went wronggg..");
            }
        }

    }

    static class CallableClass implements Callable<String> { // Класс, реализующий интерфейс Callable

        private final int i;

        CallableClass(int i) {
            this.i = i;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(2000);
            if (i == 4) throw new RuntimeException("ooops");
            return Thread.currentThread().getName(); // наименование потока, выполняющего callable задачу
        }
    }

}

/**
 * вывод в консоль:
 *
 * 13:21:43  pool-1-thread-1
 * 13:21:45  pool-1-thread-2
 * 13:21:45  pool-1-thread-3
 * something went wronggg..
 * something went wronggg..
 * 13:21:47  pool-1-thread-2
 * 13:21:47  pool-1-thread-3
 * 13:21:47  pool-1-thread-1
 * 13:21:49  pool-1-thread-2
 * 13:21:49  pool-1-thread-3
 *
 * Process finished with exit code 0
 */
