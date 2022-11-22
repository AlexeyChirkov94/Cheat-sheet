package ru.chirkov.cheat.sheet.multithreading.queue.not.blocking;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * В примере использования неблокирующей очереди ConcurrentLinkedDeque создаются два потока.
 * Первый поток Producer с задержками в 200 мс помещает в очередь 10 текстовых сообщений.
 * В зависимости от размера очереди (четное/нечетное) элемент помещается либо в хвост очереди, либо в начало.
 * Второй поток Consumer проверяет наполнение очереди и извлекает из очереди сообщения с задержкой 500 мс.
 * Consumer также проверяет четность размера очереди и извлекает элемент либо из головы, либо из хвоста очереди.
 *
 * Так как задержки в Producer меньше, чем в Consumer, то очередь наполняется быстрее, чем опустошается.
 */
public class ConcurrentLinkedDequeExample {
    private static ConcurrentLinkedDeque<String> queue;

    public static void main(String[] args) throws InterruptedException {
        queue = new ConcurrentLinkedDeque<String>();
        Thread producer = new Thread(ConcurrentLinkedDequeExample::producer);
        Thread consumer = new Thread(ConcurrentLinkedDequeExample::consumer);
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

    public static void producer() {
        System.out.println("Producer started");
        String name = "producer ";
        String ins  = "";
        for (int i = 0; i < 10; i++) {
            String element = "'" + name + i + "'";
            if (queue.size() % 2 == 1) {
                queue.addFirst(element);
                ins = "addFirst (" + element + ")";
            } else {
                queue.addLast(element);
                ins = "addLast (" + element + ")";
            }
            System.out.println(name + ins + ": queue.size()=" + queue.size());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {}
        }
    }

    public static void consumer() {
        System.out.println("Consumer started");
        for (int i = 0; i < 10; i++) {
            String text = "\n   consumer : queue.size()=" + queue.size();
            String element;
            if (queue.size() % 2 == 1)
                element = "pollFirst : " + queue.pollFirst();
            else
                element = "pollLast : " + queue.pollLast();
            text += ", " + element;
            System.out.println(text);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }
    }
}

/**
 * вывод в консоль:
 *
 * Producer started
 * Consumer started
 *
 *    consumer : queue.size()=0, pollLast : null
 * producer addLast ('producer 0'): queue.size()=1
 * producer addFirst ('producer 1'): queue.size()=2
 * producer addLast ('producer 2'): queue.size()=3
 *
 *    consumer : queue.size()=3, pollFirst : 'producer 1'
 * producer addLast ('producer 3'): queue.size()=3
 * producer addFirst ('producer 4'): queue.size()=4
 * producer addLast ('producer 5'): queue.size()=4
 *
 *    consumer : queue.size()=4, pollLast : 'producer 3'
 * producer addLast ('producer 6'): queue.size()=5
 * producer addFirst ('producer 7'): queue.size()=6
 *
 *    consumer : queue.size()=6, pollLast : 'producer 6'
 * producer addFirst ('producer 8'): queue.size()=6
 * producer addLast ('producer 9'): queue.size()=7
 *
 *    consumer : queue.size()=7, pollFirst : 'producer 8'
 *
 *    consumer : queue.size()=6, pollLast : 'producer 9'
 *
 *    consumer : queue.size()=5, pollFirst : 'producer 7'
 *
 *    consumer : queue.size()=4, pollLast : 'producer 5'
 *
 *    consumer : queue.size()=3, pollFirst : 'producer 4'
 *
 *    consumer : queue.size()=2, pollLast : 'producer 2'
 *
 * Process finished with exit code 0
 */