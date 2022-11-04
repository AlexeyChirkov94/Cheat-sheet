package ru.chirkov.cheat.sheet.multithreading.lockers.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread thread1 = new Thread(task::threadOne);
        Thread thread2 = new Thread(task::threadTwo);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        task.showCounter();
    }
}

class Task {

    private int counter = 0;
    private Lock lock = new ReentrantLock();

    public void threadOne() {
        lock.lock();
        increment();
        lock.unlock();
    }

    public void threadTwo() {
        lock.lock();
        increment();
        lock.unlock();
    }

    public void showCounter(){
        System.out.println("counter = " + counter);
    }

    private void increment() {
//        lock.lock();                      //или так
        for (int i = 0; i<10000; i++)
            counter++;
//        lock.unlock();
    }

}
