package ru.chirkov.cheat.sheet.multithreading.common.deadLock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        Thread thread1 = new Thread(runner::firstThread);
        Thread thread2 = new Thread(runner::secondThread);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        runner.finish();
    }
}

class Runner {
    private Account account1 = new Account();
    private Account account2 = new Account();
    public Random random = new Random();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public void firstThread() {
        for (int i =0 ; i< 10000; i++) {
            getLocks(lock1, lock2);
            Account.transferMoney(account1, account2, random.nextInt(100));
            lock1.unlock();
            lock2.unlock();
        }
    }

    public void secondThread() {
        for (int i =0 ; i< 10000; i++) {
            getLocks(lock2, lock1);
            Account.transferMoney(account2, account1, random.nextInt(100));
            lock1.unlock();
            lock2.unlock();
        }
    }

    public void finish(){
        System.out.println("account 1 balance = " + account1.deposit);
        System.out.println("account 2 balance = " + account2.deposit);
        System.out.println("common balance = " + (account1.deposit + account2.deposit));
    }

    private void getLocks(Lock lock1, Lock lock2){
        boolean lock1Taken = false;
        boolean lock2Taken = false;
        while (true){
            lock1Taken = lock1.tryLock(); //true - если удалось взять
            lock2Taken = lock2.tryLock(); //false - если нет

            if (lock1Taken && lock2Taken) return;
            if (lock1Taken) lock1.unlock();
            if (lock2Taken) lock2.unlock();

            try {
                Thread.sleep(1); //что бы второй поток укпел взять локи
            } catch (InterruptedException e) {}
        }
    }

}

class Account {
    int deposit = 10000;
    public static void transferMoney(Account from, Account to, int amount){
        from.deposit = from.deposit - amount;
        to.deposit = to.deposit + amount;
    }
}

/**
 * account 1 balance = 4302
 * account 2 balance = 15698
 * common balance = 20000
 *
 * Process finished with exit code 0
 */

