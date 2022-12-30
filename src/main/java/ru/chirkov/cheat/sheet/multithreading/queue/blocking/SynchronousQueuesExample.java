package ru.chirkov.cheat.sheet.multithreading.queue.blocking;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * В примере SynchQueuesExample создаются два потока, которые работают с очередью SynchronousQueue.
 * Первый поток Producer вставляет элементы в очередь, а второй поток Consumer с задержкой в 3 сек. извлекает из очереди элементы.
 * Перед каждой и после каждой операции вставки и чтения выводятся соответствующие сообщения.
 * Основная идея примера — проверка ожидания выполнения операций внесения и извлечения в синхронной очереди.
 */
public class SynchronousQueuesExample {

    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private static BlockingQueue<String> drop;
    private static final String  DONE = "done";
    private static final String[]  messages = {"Мама пошла готовить обед",
                                        "Мама позвала к столу",
                                        "Дети кушают молочную кашу",
                                        "А что ест папа?"};
    private static final String BEFORE_PUT = "*** %s before put message";
    private static final String AFTER_PUT  = "*** %s after put message\n";
    private static final String BEFORE_TAKE = "--- %s before take message";
    private static final String AFTER_TAKE  = "--- %s after take message ---\n";

    public static void main(String[] args) throws InterruptedException {
        drop = new SynchronousQueue<>();
        Thread producer = new Thread(SynchronousQueuesExample::producer);
        Thread consumer = new Thread(SynchronousQueuesExample::consumer);
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

    private static void producer() {
        try {
            for (int i = 0; i < messages.length; i++) {
                printMessage(BEFORE_PUT);
                drop.put(messages[i]);
                printMessage(AFTER_PUT);
            }
            drop.put(DONE);
        } catch (InterruptedException e) {
            System.err.println("Interrupted! " + e.getMessage());
        }
    }

    private static void consumer() {
        try {
            String msg = null;
            while (true) {
                printMessage(BEFORE_TAKE);
                Thread.sleep(3000);
                if (!((msg = drop.take()).equals(DONE))) {
                    System.out.println(msg);
                } else
                    break;
                printMessage(AFTER_TAKE);
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted! " + e.getMessage());
        }
    }

    private static void printMessage(final String message) {
        System.out.println(String.format(message, sdf.format(new Date())));
    }

}

/**
 * console output:
 *
 * *** 16:51:44 before put message
 * --- 16:51:44 before take message
 * Мама пошла готовить обед
 * *** 16:51:47 after put message
 * --- 16:51:47 after take message ---
 *
 * *** 16:51:47 before put message
 * --- 16:51:47 before take message
 * Мама позвала к столу
 * *** 16:51:50 after put message
 * --- 16:51:50 after take message ---
 *
 * *** 16:51:50 before put message
 * --- 16:51:50 before take message
 * Дети кушают молочную кашу
 * --- 16:51:53 after take message ---
 * *** 16:51:53 after put message
 *
 * --- 16:51:53 before take message
 * *** 16:51:53 before put message
 * А что ест папа?
 * *** 16:51:56 after put message
 * --- 16:51:56 after take message ---
 *
 * --- 16:51:56 before take message
 *
 * Process finished with exit code 0
 */
