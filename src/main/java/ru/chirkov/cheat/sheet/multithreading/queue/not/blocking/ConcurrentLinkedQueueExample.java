package ru.chirkov.cheat.sheet.multithreading.queue.not.blocking;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * В примере использования неблокирующей очереди ConcurrentLinkedQueueExample создаются два потока.
 * Первый поток Producer с задержками в 200 мс помещает в очередь 10 текстовых сообщений.
 * Второй поток Consumer проверяет наполнение очереди и извлекает из очереди сообщения с задержкой 500 мс.
 * Таким образом, очередь наполняется быстрее, чем опустошается.
 */
public class ConcurrentLinkedQueueExample {

    private static volatile boolean cycle = true;
    private static Queue<String> queue = null;

    public static void main(String[] args) throws InterruptedException {
        queue = new ConcurrentLinkedQueue<>();
        Thread producer = new Thread(ConcurrentLinkedQueueExample::producer);
        Thread consumer = new Thread(ConcurrentLinkedQueueExample::consumer);
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

    public static void producer() {
        System.out.println("Producer started");
        try {
            for (int i = 1; i <= 10; i++) {
                String str = "String" + i;
                queue.add(str);
                System.out.println("Producer added : " + str);
                Thread.sleep(200);
            }
            cycle = false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void consumer() {
        String str;
        System.out.println("Consumer started\n");
        while (cycle || queue.size() > 0) {
            if ((str = queue.poll()) != null)
                System.out.println("  consumer removed : " + str);
            try {
                Thread.sleep(500);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

/**
 * вывод в консоль:
 *
 * Producer started
 * Consumer started
 *
 * Producer added : String1
 * Producer added : String2
 * Producer added : String3
 *   consumer removed : String1
 * Producer added : String4
 * Producer added : String5
 *   consumer removed : String2
 * Producer added : String6
 * Producer added : String7
 * Producer added : String8
 *   consumer removed : String3
 * Producer added : String9
 * Producer added : String10
 *   consumer removed : String4
 *   consumer removed : String5
 *   consumer removed : String6
 *   consumer removed : String7
 *   consumer removed : String8
 *   consumer removed : String9
 *   consumer removed : String10
 *
 * Process finished with exit code 0
 */
