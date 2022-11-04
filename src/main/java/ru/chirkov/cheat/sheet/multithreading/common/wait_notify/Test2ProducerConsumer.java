package ru.chirkov.cheat.sheet.multithreading.common.wait_notify;

import java.util.LinkedList;
import java.util.Queue;

public class Test2ProducerConsumer {

    private Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 10;
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException{
        ProducerConsumer pc = new ProducerConsumer();

        Thread produceThread = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumeThread = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


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


    public void produce() throws InterruptedException {
        int value  = 0;
        while(true){
            synchronized (lock){
                while (queue.size() <= LIMIT){
                    lock.wait();
                }

                queue.offer(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException{

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
    }
}
