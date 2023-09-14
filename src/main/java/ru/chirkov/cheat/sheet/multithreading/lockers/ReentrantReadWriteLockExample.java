package ru.chirkov.cheat.sheet.multithreading.lockers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * Пример демострирует работу ReadWriteLock.
 * Когда пишуший поток запросил блокировку, новые потоки на чтение перечтали обрабатываться и стали ждать пока пищущий
 * поток закончит работу.
 * В то же время пищущий поток начал работу только когда все читающие потоки (получившие блокировку раньше) закончили чтение.
 */
public class ReentrantReadWriteLockExample {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
    private static final ReadWriteLock rwl = new ReentrantReadWriteLock();
    private static final long READ_TIME = 1000L;
    private static final long WRITE_TIME = 10000L;
    private static final long PAUSE_TIME = 100L;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(5);

        exec.submit(ReentrantReadWriteLockExample::read); pause();
        exec.submit(ReentrantReadWriteLockExample::read); pause();
        exec.submit(ReentrantReadWriteLockExample::write); pause();
        exec.submit(ReentrantReadWriteLockExample::read); pause();
        exec.submit(ReentrantReadWriteLockExample::read); pause();
        System.out.println("___All threads trying start!");

        exec.shutdown();
    }

    private static void read() {
        System.out.println(Thread.currentThread().getName() + " try start read, time = " + sdf.format(new Date()));
        rwl.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + " start read, time = " + sdf.format(new Date()));
            Thread.sleep(READ_TIME);
            System.out.println(Thread.currentThread().getName() + " end read, time = " + sdf.format(new Date()));
        } catch (InterruptedException e){}
        finally {
            rwl.readLock().unlock();
        }
    }


    private static void write() {
        System.out.println(Thread.currentThread().getName() + " try start write, time = " + sdf.format(new Date()));
        rwl.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + " start write, time = " + sdf.format(new Date()));
            Thread.sleep(WRITE_TIME);
            System.out.println(Thread.currentThread().getName() + " end write, time = " + sdf.format(new Date()));
        } catch (InterruptedException e){}
        finally {
            rwl.writeLock().unlock();
        }
    }

    private static final void pause(){
        try {
            Thread.sleep(PAUSE_TIME);
        } catch (InterruptedException e){}
    }
}

/**
 * Console output:
 *
 * pool-1-thread-1 try start read, time = 31:31
 * pool-1-thread-1 start read, time = 31:31
 * pool-1-thread-2 try start read, time = 31:31
 * pool-1-thread-2 start read, time = 31:31
 * pool-1-thread-3 try start write, time = 31:31
 * pool-1-thread-4 try start read, time = 31:31
 * pool-1-thread-5 try start read, time = 31:31
 * ___All threads trying start!
 * pool-1-thread-1 end read, time = 31:32
 * pool-1-thread-2 end read, time = 31:32
 * pool-1-thread-3 start write, time = 31:32
 * pool-1-thread-3 end write, time = 31:42
 * pool-1-thread-4 start read, time = 31:42
 * pool-1-thread-5 start read, time = 31:42
 * pool-1-thread-5 end read, time = 31:43
 * pool-1-thread-4 end read, time = 31:43
 *
 * Process finished with exit code 0
 */
