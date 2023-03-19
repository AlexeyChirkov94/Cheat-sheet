package ru.chirkov.cheat.sheet.multithreading.ConcurrencyInPracticeExamples.demoDeadLock;

import java.util.*;

/**
 * DemonstrateDeadlock
 * <p/>
 * Driver loop that induces deadlock under typical conditions
 *
 * @author Brian Goetz and Tim Peierls
 */
public class DemonstrateDeadlock {
    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 1_000_000;

    public static void main(String[] args) {
        final Random rnd = new Random();
        Account[] accounts = new Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(10_000);
        }

        class TransferThread extends Thread {
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    DollarAmount amount = new DollarAmount(rnd.nextInt(1000));
                    try {
                        DynamicOrderDeadlock.transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                    } catch (DynamicOrderDeadlock.InsufficientFundsException ignored) {}
                }
                System.out.println("I`m finish"); //will never appear because of deadlock
            }
        }

        for (int i = 0; i < NUM_THREADS; i++)
            new TransferThread().start();
    }
}
