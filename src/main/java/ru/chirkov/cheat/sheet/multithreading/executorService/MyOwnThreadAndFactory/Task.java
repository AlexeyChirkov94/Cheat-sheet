package ru.chirkov.cheat.sheet.multithreading.executorService.MyOwnThreadAndFactory;

class Task implements Runnable{

    private int id;
    private long waiting;

    public Task(int id, long waiting) {
        this.id = id;
        this.waiting = waiting;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(waiting);
        } catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("task " + id + " finish");
    }

}
