package ru.chirkov.cheat.sheet.multithreading.common.wait_notify;

import java.util.Scanner;

public class TestWaitAndNotify {

    public static void main(String[] args) throws InterruptedException{
        WaitAndNotify wn = new WaitAndNotify();

        Thread produceThread = new Thread(() -> {
            try {
                wn.produce();
            } catch (InterruptedException e) {}
        });

        Thread consumeThread = new Thread(() -> {
            try {
                wn.consume();
            } catch (InterruptedException e) {}
        });

        produceThread.start();
        consumeThread.start();
        produceThread.join();
        consumeThread.join();
    }
}

class WaitAndNotify {
    private Object lock1 = new Object();
    public void produce() throws InterruptedException {
        synchronized (lock1){
            System.out.println("Producer thread started");
            lock1.wait(); // 1. отдает intrinsic lock, 2.ждет когда будет вызван notify() или (notifyAll())
            System.out.println("Producer thread resume");
        }
    }

    public void consume() throws InterruptedException{
        Thread.sleep(2000);
        Scanner scanner = new Scanner(System.in);
        synchronized (lock1) {
            System.out.println("Waiting for return key pressed");
            scanner.nextLine();
            lock1.notify(); //пробуждает 1 поток (в отличии от notifyAll, но не отдает intrinsic lock),
            // поэтмоу выполнение метода produce() начнется полько после выхода из синхноризированного блока,
            // (если бы синхроонизация была бы на методе, то после метода)
            System.out.println("this.notify()");
        }
        Thread.sleep(5000); // эти 5 сек не ждет, выполняется сразу. (т.к. эта часть метода не синхронизированна)
    }
}

/**
 * вывод в консоль:
 *
 * Producer thread started
 * Waiting for return key pressed
 * <- (Me put Enter)
 *
 * this.notify()
 * Producer thread resume
 *
 *
 * Process finished with exit code 0
 */