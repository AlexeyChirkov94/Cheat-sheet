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
