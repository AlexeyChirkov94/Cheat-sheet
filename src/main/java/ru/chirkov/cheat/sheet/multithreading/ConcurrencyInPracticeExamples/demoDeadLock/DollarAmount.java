package ru.chirkov.cheat.sheet.multithreading.ConcurrencyInPracticeExamples.demoDeadLock;

public class DollarAmount {

    private int balance;

    public DollarAmount(int balance) {
        this.balance = balance;
    }

    public DollarAmount add(DollarAmount d) {
        return new DollarAmount(balance + d.balance);
    }

    public DollarAmount subtract(DollarAmount d) {
        return new DollarAmount(balance - d.balance);
    }

    public int compareTo(DollarAmount dollarAmount) {
        return 0;
    }

}
