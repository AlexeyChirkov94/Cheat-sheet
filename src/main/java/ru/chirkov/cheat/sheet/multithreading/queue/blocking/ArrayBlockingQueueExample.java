package ru.chirkov.cheat.sheet.multithreading.queue.blocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * В примере создается очередь drop типа ArrayBlockingQueue емкостью в один элемент и с установленом флагом справедливости.
 * После этого запускаются два потока. Первый поток Producer помещает в очередь сообщения из массива messages
 * с использованием метода put, а второй поток Consumer считывает из очереди сообщения методом take и выводит их в консоль.
 */
public class ArrayBlockingQueueExample {

    private static BlockingQueue<String> drop;
    private static final String    DONE     = "done";
    private static final String[]  messages = {
            "Мама пошла готовить обед",
            "Мама позвала к столу",
            "Дети кушают молочную кашу",
            "А что ест папа?"};

    public static void main(String[] args) {
        drop = new ArrayBlockingQueue<>(1, true);
        Thread producer = new Thread(ArrayBlockingQueueExample::producer);
        Thread consumer = new Thread(ArrayBlockingQueueExample::consumer);
        producer.start();
        consumer.start();
    }


    private static void producer() {
        try {
            int cnt = 0;
            for (int i=0; i<messages.length; i++) {
                drop.put(messages[i]);
                if (++cnt < 3)
                    Thread.sleep(5000);
            }
            drop.put(DONE);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void consumer() {
        try {
            String msg;
            while (!((msg = drop.take()).equals(DONE)))
                System.out.println(msg);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

}

/**
 * console output:
 *
 * Мама пошла готовить обед
 * Мама позвала к столу
 * Дети кушают молочную кашу
 * А что ест папа?
 *
 * Process finished with exit code 0
 */