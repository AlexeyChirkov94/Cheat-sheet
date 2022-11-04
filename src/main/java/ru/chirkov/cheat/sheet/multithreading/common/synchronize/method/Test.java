package ru.chirkov.cheat.sheet.multithreading.common.synchronize.method;


import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    private Object lock = new Object();
    private AtomicInteger atomicCounter = new AtomicInteger(0) ;
    private Integer intCounter = 0;

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        test.doWork();
    }

    public void doWork() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++)
//                notSyncIncrement(); // ~2.0 sec
//                syncMethIncrement();  // ~5.1 sec
                syncBlockIncrement(); // ~4.0 sec
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++)
//                notSyncIncrement();
//                syncMethIncrement();
                syncBlockIncrement();
        });

        Long start = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        Long end = System.currentTimeMillis();

        System.out.println("atomicCounter = " + atomicCounter);
        System.out.println("intCounter    = " + intCounter);
        System.out.println("spend time    = " + (end-start));
    }

    private void notSyncIncrement(){
        atomicCounter.addAndGet(1);
    }

    synchronized private void syncMethIncrement(){
        intCounter++;
    }

    private void syncBlockIncrement(){
        synchronized (lock){
            intCounter++;
        }
    }

}
