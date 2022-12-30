package ru.chirkov.cheat.sheet.multithreading.lockers.reentrantLock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * В примере ReentrantLockExample, листинг которого представлен ниже, используется внутренний класс LockClass
 * для организации двух потоков. Константы TIME_WAIT и TIME_SLEEP используются потоками для организации определенных
 * задержек при выполнении. Текстовая переменная resource используется в качестве общего ресурса, значение которого
 * будет изменяться внутри потоков. Метод printMessage выводит в консоль сообщения потоков с указанием времени.
 *
 * В конструкторе примера создается блокировка lock типа ReentrantLock и два потока, которые будут использовать lock
 * для блокирования доступа к текстовому ресурсу. Сначала каждый поток пытается в течение определенного времени
 * (TIME_WAIT, мс) блокировать доступ к ресурсу resource с использованием метода tryLock. Если блокировка получена,
 * то текст строки resource изменяется. После этого в потоке выполняется некоторая задержка по времени (TIME_SLEEP, мс)
 * и поток завершает свою работу с освобождением блокировки методом unlock. Если поток в течение времени TIME_WAIT не
 * смог блокировать ресурс, то он переходит к стадии задержки и завершению работы.
 *
 * Оперируя временем ожидания блокировки TIME_WAIT и временем задержки TIME_SLEEP можно дать возможность либо
 * каждому из потоку изменить значение resource, либо только одному.
 */
public class ReentrantLockExample  {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss  ");

    private static String resource = "Hello, World!";
    private static Lock lock;
    private final static int TIME_WAIT  = 1500;
    private final static int TIME_SLEEP = 1000;

    public static void main(String[] args) throws InterruptedException {
        lock = new ReentrantLock();
        Thread thread1 = new Thread(new LockClass("first","Первый поток"));
        Thread thread2 = new Thread(new LockClass("second","Второй поток"));
        thread1.start();
        thread2.start();
        printMessage(null);

        thread1.join();
        thread2.join();
        System.out.println("\nЗавершение работы примера");
        System.exit(0);
    }

    private static class LockClass implements Runnable {
        String name;
        String text;

        public LockClass(String name, String text) {
            this.name = name;
            this.text = text;
        }

        @Override
        public void run() {
            boolean locked = false;
            try {
                locked = lock.tryLock(TIME_WAIT, TimeUnit.MILLISECONDS); // Получение блокировки в течение TIME_WAIT
                if (locked) {
                    resource = text;
                    printMessage(null);
                }
                Thread.sleep(TIME_SLEEP);
            } catch (InterruptedException ignore) {
            } finally{
                String text = name + " : завершил работу";
                printMessage(text);
                if (locked) lock.unlock();
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
 * (TIME_WAIT  < TIME_SLEEP) :
 * 21:27:37  Hello, World!
 * 21:27:37  Первый поток
 * 21:27:39  first : завершил работу
 * 21:27:40  second : завершил работу
 *
 * Завершение работы примера
 *
 * (TIME_WAIT  > TIME_SLEEP) :
 * 21:28:52  Hello, World!
 * 21:28:52  Первый поток
 * 21:28:53  first : завершил работу
 * 21:28:53  Второй поток
 * 21:28:54  second : завершил работу
 *
 * Завершение работы примера
 * Process finished with exit code 0
 */


