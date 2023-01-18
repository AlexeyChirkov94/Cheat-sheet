package ru.chirkov.cheat.sheet.multithreading.lockers;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Пример ReentrantCondExample демонстрирует использование объекта условия Condition с блокировкой ReentrantLock.
 * В примере описывается торговый склад, в который производитель завозит товар из списка GOODS.
 * Товар регистрируется в коллекции goods. Потребитель забирает товар со склада.
 *
 * В конструкторе примера создаются торговый склад store и два потока : producer, consumer.
 * Метод printMessage выводит сообщения потоков в консоль.
 */
public class ReentrantCondExample  {

    private static Store             store = null;
    private static final String[]    GOODS = {"Молоко", "Кефир", "Ряженка", "Кофе", "Чай"};
    private static List<String>      goods = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        store = new Store();
        Thread producer = new Thread(ReentrantCondExample::produce);
        Thread consumer = new Thread(ReentrantCondExample::consume);
        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
        System.out.println("\nЗавершение работы примера");
        System.exit(0);
    }

    private static class Store { // Склад с товаром
        ReentrantLock  lock;  // блокировка
        Condition      cond;  // условие блокировки

        Store() {
            lock = new ReentrantLock();
            cond = lock.newCondition();
        }

        private void get() {
            lock.lock();
            try {
                while (goods.isEmpty()) cond.await(); // ожидание на пустом складе (аналог Object.wait())

                printMessage("Реализация : " + goods.get(0));
                goods.remove(0);
                printMessage(null);

                cond.signalAll(); // Сигнализация (аналог Object.NotifyAll())
            } catch (InterruptedException e){}
            finally{
                lock.unlock();
            }
        }

        private void put(final String good) {
            lock.lock();
            try {
                while (goods.size() >= 3) cond.await(); // ожидание освобождения места (аналог Object.wait())

                goods.add(good);
                printMessage("Доставка : " + good);
                printMessage(null);

                cond.signalAll();  // Сигнализация (аналог Object.NotifyAll())
            } catch (InterruptedException e){ }
            finally{
                lock.unlock();
            }
        }
    }

    private static void produce() {
        for (int i = 0; i < GOODS.length; i++) {
            store.put(GOODS[i]);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) { }
        }
    }

    private static void consume(){
        for (int i = 0; i < GOODS.length; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) { }
            store.get();
        }
    }

    private static void printMessage(final String msg)  {
        if (msg != null) {
            System.out.println(new SimpleDateFormat("HH:mm:ss  ").format(new Date()) + msg);
        } else
            System.out.println("\tТоваров на складе: " + goods.size());
    }

}

/**
 * вывод в консоль:
 *
 * 15:45:07  Доставка : Молоко
 * 	Товаров на складе: 1
 * 15:45:08  Доставка : Кефир
 * 	Товаров на складе: 2
 * 15:45:08  Доставка : Ряженка
 * 	Товаров на складе: 3
 * 15:45:09  Реализация : Молоко
 * 	Товаров на складе: 2
 * 15:45:09  Доставка : Кофе
 * 	Товаров на складе: 3
 * 15:45:11  Реализация : Кефир
 * 	Товаров на складе: 2
 * 15:45:11  Доставка : Чай
 * 	Товаров на складе: 3
 * 15:45:13  Реализация : Ряженка
 * 	Товаров на складе: 2
 * 15:45:15  Реализация : Кофе
 * 	Товаров на складе: 1
 * 15:45:17  Реализация : Чай
 * 	Товаров на складе: 0
 *
 * Завершение работы примера
 *
 * Process finished with exit code 0
 */
