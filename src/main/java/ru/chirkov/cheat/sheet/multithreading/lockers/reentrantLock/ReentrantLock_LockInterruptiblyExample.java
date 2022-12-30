package ru.chirkov.cheat.sheet.multithreading.lockers.reentrantLock;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.text.SimpleDateFormat;


/**
 * Основная идея примера LockInterruptiblyExample связана с тем, чтобы в очередь (в ожидание) на получение блокировки
 * поставить два потока, а в первом потоке, получившем блокировку, прервать работу одного из потоков.
 *
 * Следующий код демонстрирует старт трёх потоков; второй и третий потоки стартуют с небольшой задержкой, чтобы надежно
 * первый поток захватил блокировку lock, в противном случае первым захватить блокировку может и второй поток, работу
 * которого необходимо прерывать.
 *
 */
public class ReentrantLock_LockInterruptiblyExample {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss  ");
    private static String resource = "Hello, World!";
    private static Lock lock;
    final static int  TIME_SLEEP = 7000;

    private static Thread thread1;
    private static Thread thread2;
    private static Thread thread3;

    public static void main(String[] args) throws InterruptedException {
        lock = new ReentrantLock();
        thread1 = new Thread(new LockClass("first" , "Текст первого потока"));
        thread2 = new Thread(new LockClass("second", "Текст второго поток"));
        thread3 = new Thread(new LockClass("third" , "Текст третего поток"));
        thread1.start();
        Thread.sleep(400);
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println("\nЗавершение работы примера");
        System.exit(0);
    }

    private static class LockClass implements Runnable {
        String name;
        String text;
        public LockClass(final String name, final String text) {
            this.name = name;
            this.text = text;
        }

        @Override
        public void run() {
            try {
                printMessage("Wait (" + name + ") ...");
                lock.lockInterruptibly();
                try {
                    Thread.sleep(2000);
                    if (name.equalsIgnoreCase("first")) {
                        printMessage("Прерывание второго потока");
                        thread2.interrupt();
                        thread2.join();
                    }

                    resource = text; // доступ к ресурсу
                    printMessage(null);
                    Thread.sleep(TIME_SLEEP);
                } finally {
                    lock.unlock();
                    String text = name + " : завершил работу";
                    printMessage(text);
                }
            } catch (InterruptedException e) {
                printMessage(name + " : ожидание прервано");
            }
        }
    }

    private static void printMessage(final String msg) {
        String text = sdf.format(new Date());
        if (msg == null) text += resource;
        else text += msg;
        System.out.println(text);
    }

}

/**
 * вывод в консоль:
 *
 * Ниже представлены сообщения потоков. Сначала потоки запрашивают блокировку, первый поток, пришедший первым, получает ее,
 * а два потока остаются в ожидании освобождения/получения блокировки. После небольшой задержки первый поток прерывает
 * работу второго потока и ждет его завершения. Далее первый поток освобождает блокировку и завершает работу. Сразу же
 * после этого третий поток получает блокировку и далее по сценарию.
 *
 * 22:02:04  Wait (first) ...
 * 22:02:04  Wait (second) ...
 * 22:02:04  Wait (third) ...
 * 22:02:06  Прерывание второго потока
 * 22:02:06  second : ожидание прервано
 * 22:02:06  Текст первого потока
 * 22:02:13  first : завершил работу
 * 22:02:15  Текст третего поток
 * 22:02:22  third : завершил работу
 *
 * Завершение работы примера
 */
