package ru.chirkov.cheat.sheet.multithreading.ConcurrencyInPracticeExamples.demoDeadLock;

import java.util.concurrent.atomic.AtomicInteger;

public class Account {

    private DollarAmount balance;
    private final int acctNo;
    private static final AtomicInteger sequence = new AtomicInteger();

    public Account(int initialBalance) {
        balance = new DollarAmount(initialBalance);
        acctNo = sequence.incrementAndGet();
    }

    void debit(DollarAmount d) {
        balance = balance.subtract(d);
    }

    void credit(DollarAmount d) {
        balance = balance.add(d);
    }

    DollarAmount getBalance() {
        return balance;
    }

    int getAcctNo() {
        return acctNo;
    }

}
