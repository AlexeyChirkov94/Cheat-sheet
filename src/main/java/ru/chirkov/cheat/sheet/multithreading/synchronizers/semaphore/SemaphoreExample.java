package ru.chirkov.cheat.sheet.multithreading.synchronizers.semaphore;

import java.util.concurrent.Semaphore;

/**
 * https://java-online.ru/concurrent-synchronizers.xhtml#semaphore
 *
 * В примере несколько всадников с лошадьми должны пройти контроль перед скачками. Количество контроллеров меньше
 * количества всадников, поэтому некоторые всадники будут дожидаться, пока не освободиться один из контроллеров.
 *
 * Общий ресурс CONTROL_PLACES, символизирующий контролеров и используемый всеми потоками, выделен оператором synchronized.
 * С помощью семафора осуществляется контроль доступа только одному потоку.
 */
public class SemaphoreExample  {

    private static final int COUNT_CONTROL_PLACES = 5;
    private static final int COUNT_RIDERS         = 7;
    private static boolean[] CONTROL_PLACES = null; // Флаги мест контроля
    private static Semaphore SEMAPHORE = null; // Семафор

    public static void main(String[] args) throws InterruptedException  {

        CONTROL_PLACES = new boolean[COUNT_CONTROL_PLACES];  // Определяем количество мест контроля

        for (int i = 0; i < COUNT_CONTROL_PLACES; i++)  // Флаги мест контроля [true-свободно,false-занято]
            CONTROL_PLACES[i] = true;

// Определяем семафор со следующими параметрами :
// - количество разрешений 5
// - флаг очередности fair=true (очередь first_in-first_out)
        SEMAPHORE = new Semaphore(CONTROL_PLACES.length, true);

        for (int i = 1; i <= COUNT_RIDERS; i++) {
            new Thread(new Rider(i)).start();
            Thread.sleep(400);
        }
    }

    public static class Rider implements Runnable  {
        private int ruderNum;
        public Rider(int ruderNum)  {
            this.ruderNum = ruderNum;
        }

        @Override
        public void run() {
            System.out.printf("Всадник %d подошел к зоне контроля\n", ruderNum);
            try {
                // Запрос разрешения
                SEMAPHORE.acquire();
                System.out.printf("\tвсадник %d проверяет наличие свободного контроллера\n", ruderNum);
                int controlNum = -1;
                // Ищем свободное место и подходим к контроллеру
                synchronized (CONTROL_PLACES) {
                    for (int i = 0; i < COUNT_CONTROL_PLACES; i++)
                        // Есть ли свободные контроллеры?
                        if (CONTROL_PLACES[i]) {
                            // Занимаем место
                            CONTROL_PLACES[i] = false;
                            controlNum = i;
                            System.out.printf("\t\tвсадник %d подошел к контроллеру %d.\n", ruderNum, i);
                            break;
                        }
                }

                Thread.sleep((int) (Math.random() * 10+1)*1000); // Время проверки лошади и всадника
                // Освобождение места контроля
                synchronized (CONTROL_PLACES) {
                    CONTROL_PLACES[controlNum] = true;
                }

                // Освобождение ресурса
                SEMAPHORE.release();
                System.out.printf("Всадник %d завершил проверку\n", ruderNum);
            } catch (InterruptedException e) {}
        }
    }

}

/**
 * вывод в консоль:
 *
 * Всадник 1 подошел к зоне контроля
 * 	всадник 1 проверяет наличие свободного контроллера
 * 		всадник 1 подошел к контроллеру 0.
 * Всадник 2 подошел к зоне контроля
 * 	всадник 2 проверяет наличие свободного контроллера
 * 		всадник 2 подошел к контроллеру 1.
 * Всадник 3 подошел к зоне контроля
 * 	всадник 3 проверяет наличие свободного контроллера
 * 		всадник 3 подошел к контроллеру 2.
 * Всадник 4 подошел к зоне контроля
 * 	всадник 4 проверяет наличие свободного контроллера
 * 		всадник 4 подошел к контроллеру 3.
 * Всадник 5 подошел к зоне контроля
 * 	всадник 5 проверяет наличие свободного контроллера
 * 		всадник 5 подошел к контроллеру 4.
 * Всадник 6 подошел к зоне контроля
 * Всадник 7 подошел к зоне контроля
 * Всадник 3 завершил проверку
 * 	всадник 6 проверяет наличие свободного контроллера
 * 		всадник 6 подошел к контроллеру 2.
 * Всадник 6 завершил проверку
 * 	всадник 7 проверяет наличие свободного контроллера
 * 		всадник 7 подошел к контроллеру 2.
 * Всадник 2 завершил проверку
 * Всадник 1 завершил проверку
 * Всадник 4 завершил проверку
 * Всадник 7 завершил проверку
 * Всадник 5 завершил проверку
 *
 * Process finished with exit code 0
 */
