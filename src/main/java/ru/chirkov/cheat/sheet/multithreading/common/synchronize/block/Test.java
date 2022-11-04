package ru.chirkov.cheat.sheet.multithreading.common.synchronize.block;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {

    private volatile int counter;

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.main();
    }

}

class Worker {
    private Random random = new Random();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void addToList1(){
        synchronized (lock1){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("was interrupted 1");
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }

    public void addToList2() {
        synchronized (lock2){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("was interrupted 2");
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }
    }

    public void work() {
        for (int i = 0; i < 1000; i++){
            addToList1();
            addToList2();
        }
    }

    public void main() {
        long before = System.currentTimeMillis();

        Thread thread1 = new Thread(this::work);
        Thread thread2 = new Thread(this::work);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long after = System.currentTimeMillis();
        System.out.println("working time = " + (after-before) + " ms");
        System.out.println("List1 : " + list1.size());
        System.out.println("List2 : " + list2.size());
    }
}
