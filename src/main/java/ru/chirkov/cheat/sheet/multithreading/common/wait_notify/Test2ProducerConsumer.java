package ru.chirkov.cheat.sheet.multithreading.common.wait_notify;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Тест очереди условий. (Канкаренси на практике стр 380)
 * Интересно что не возникает deadlock.
 * Видимо вызывая на объекте блокировки lock метод очередей условий (wait, notify) внутренняя блокировка снимается
 * Из книги: Метод Object.wait атомарно освобождает замок и просит ОС приостановить текущий поток
 *
 * Если заменить synchronized (lock) -> synchronized (this) попадем в deadlock что как бы логично
 */
public class Test2ProducerConsumer {

    public static void main(String[] args) throws InterruptedException{
        ProducerConsumer pc = new ProducerConsumer();
        Thread produceThread = new Thread(pc::produce);
        Thread consumeThread = new Thread(pc::consume);

        produceThread.start();
        consumeThread.start();
        produceThread.join();
        consumeThread.join();
    }
}

class ProducerConsumer {

    private Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 10;
    private static final Object lock = new Object();


    public void produce() {
        int value  = 0;
        try{
            while(true){
                synchronized (lock){
                    while (queue.size() >= LIMIT){
                        lock.wait();
                    }

                    queue.offer(value++);
                    lock.notify();
                }
            }
        } catch (InterruptedException e){};

    }

    public void consume(){
        try {
            while(true) {
                synchronized (lock){
                    while (queue.size() == 0){
                        lock.wait();
                    }

                    int value = queue.poll();
                    System.out.println(value);
                    System.out.println("Size is " + queue.size());
                    lock.notify();
                }

                Thread.sleep(1000);
            }
        } catch (InterruptedException e){}

    }
}

/**
 * вывод в консоль:
 *
 * 0
 * Size is 3
 * 1
 * Size is 9
 * 2
 * Size is 9
 * 3
 * Size is 9
 * 4
 * Size is 9
 * ..... (same)
 */
