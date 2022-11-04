package ru.chirkov.cheat.sheet.multithreading.common.run2Thread;

public class WithMain {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        MyThread myThread2 = new MyThread();
        myThread.start();
        myThread2.start();

//        Thread thread1 = new Thread(new Runner());
//        Thread thread2 = new Thread(new Runner());
//        thread1.start();
//        thread2.start();
    }
}

class MyThread extends Thread{

    @Override
    public void run(){
        for (int i = 0; i < 1000; i++ ){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThread i=" + i );
        }
    }

}

class Runner implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++ ){
            System.out.println("Runner i=" + i );
        }
    }

}
