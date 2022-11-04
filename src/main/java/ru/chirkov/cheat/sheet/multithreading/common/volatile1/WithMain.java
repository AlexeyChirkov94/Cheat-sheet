package ru.chirkov.cheat.sheet.multithreading.common.volatile1;

import java.util.Scanner;

public class WithMain {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        myThread.shutdown();
    }
}

class MyThread extends Thread{

    private volatile boolean running = true;
    public void shutdown(){
        running = false;
    }

    @Override
    public void run(){
        while (running){
            System.out.println("Hello");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
