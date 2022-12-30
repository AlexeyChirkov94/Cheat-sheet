package ru.chirkov.cheat.sheet.multithreading.queue.blocking;

import java.util.Calendar;
import java.util.Deque;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;


/**
 * Сначала создается массив из элементов (месяцев) календаря и формируется двунаправленная блокирующая очередь типа
 * LinkedBlockingDeque с ограничением в 6 элементов. После этого стартуют два потока : Producer для добавления элементов
 * в очередь и Consumer для извлечения элемента с удалением. Задержки после выполнения операций с очередью у потоков различны,
 * Producer опережает Consumer, т.е. очередь должна быстрее наполняться.
 *
 * Поток Producer вставляет элементы из коллекции в начало очереди. Поток Consumer извлекает из очереди элементы :
 * если размер очереди нечетный, то элемент извлекается из головы, в противном случае из хвоста очереди.
 * Все сообщения выводятся в консоль. Результат работы примера после листинга.
 */
public class LinkedBlockingDequeExample {
    private static final String EXTRACT     = "Извлечение из map : %s%n"   ;
    private static final String INSERT      = "Добавление в очередь : %s%n";
    private static final String WAIT        = "... ожидание : %s%n"        ;
    private static final String SIZE        = "--- deque.size=%d ---%n"    ;
    private static final String REMOVE_HEAD = "\tremove head: %s%n"        ;
    private static final String REMOVE_TAIL = "\tremove tail: %s%n"        ;

    private static Map<String, Integer> names = null;
    private static Deque<String> deque = null;

    public static void main(String args[]) {
        Calendar now  = Calendar.getInstance();
        Locale locale = Locale.getDefault();

        names = now.getDisplayNames(Calendar.MONTH, Calendar.ALL_STYLES, locale);
        deque = new LinkedBlockingDeque<>(6);
        System.out.printf("Список коллекции : %s%n",names);


        Thread producer = new Thread(LinkedBlockingDequeExample::producer);
        Thread consumer = new Thread(LinkedBlockingDequeExample::consumer);
        producer.start();
        consumer.start();

        while (producer.isAlive()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}
        }
        System.exit(0);
    }

    private static void producer() {
        Set<String> keys = names.keySet();
        Iterator<String> iter = keys.iterator();
        String element = null;
        while ((iter.hasNext()) || (element != null)) {
            if (element == null) {
                element = iter.next();
                System.out.printf(EXTRACT, element);
            }
            // Добавление элемента в начало
            if (deque.offerFirst(element)) {
                System.out.printf(INSERT, element);
                iter.remove();
                element = null;
            } else {
                System.out.printf(WAIT, element);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ignored) {}
            }
            System.out.printf(SIZE, deque.size());
        }
        try {
            Thread.sleep(3500);
        } catch (InterruptedException ignored) {}
    }

    private static void consumer(){
        while (true) {
            if ((deque.size() % 2 == 1))
                // удаление из начала
                System.out.printf(REMOVE_HEAD,
                        deque.pollFirst());
            else
                // удаление из конца
                System.out.printf(REMOVE_TAIL,
                        deque.pollLast());
            try {
                // пауза между циклами
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}
        }
    }

}

/**
 * console output:
 *
 * Список коллекции : {September=8, December=11, February=1, November=10, January=0, October=9, August=7, April=3, March=2, July=6, June=5, Apr=3, Aug=7, Dec=11, Feb=1, Jan=0, Jul=6, Jun=5, Mar=2, May=4, Nov=10, Oct=9, Sep=8}
 * Извлечение из map : September
 * 	remove tail: null
 * Добавление в очередь : September
 * --- deque.size=1 ---
 * Извлечение из map : December
 * Добавление в очередь : December
 * --- deque.size=2 ---
 * Извлечение из map : February
 * Добавление в очередь : February
 * --- deque.size=3 ---
 * Извлечение из map : November
 * Добавление в очередь : November
 * --- deque.size=4 ---
 * Извлечение из map : January
 * Добавление в очередь : January
 * --- deque.size=5 ---
 * Извлечение из map : October
 * Добавление в очередь : October
 * --- deque.size=6 ---
 * Извлечение из map : August
 * ... ожидание : August
 * --- deque.size=6 ---
 * ... ожидание : August
 * 	remove tail: September
 * --- deque.size=5 ---
 * Добавление в очередь : August
 * --- deque.size=6 ---
 * Извлечение из map : April
 * ... ожидание : April
 * --- deque.size=6 ---
 * ... ожидание : April
 * 	remove tail: December
 * --- deque.size=5 ---
 * Добавление в очередь : April
 * --- deque.size=6 ---
 * Извлечение из map : March
 * ... ожидание : March
 * --- deque.size=6 ---
 * ... ожидание : March
 * 	remove tail: February
 * --- deque.size=5 ---
 * Добавление в очередь : March
 * --- deque.size=6 ---
 * Извлечение из map : July
 * ... ожидание : July
 * --- deque.size=6 ---
 * ... ожидание : July
 * 	remove tail: November
 * --- deque.size=5 ---
 * Добавление в очередь : July
 * --- deque.size=6 ---
 * Извлечение из map : June
 * ... ожидание : June
 * --- deque.size=6 ---
 * ... ожидание : June
 * 	remove tail: January
 * --- deque.size=5 ---
 * Добавление в очередь : June
 * --- deque.size=6 ---
 * Извлечение из map : Apr
 * ... ожидание : Apr
 * --- deque.size=6 ---
 * ... ожидание : Apr
 * 	remove tail: October
 * --- deque.size=5 ---
 * Добавление в очередь : Apr
 * --- deque.size=6 ---
 * Извлечение из map : Aug
 * ... ожидание : Aug
 * --- deque.size=6 ---
 * ... ожидание : Aug
 * 	remove tail: August
 * --- deque.size=5 ---
 * Добавление в очередь : Aug
 * --- deque.size=6 ---
 * Извлечение из map : Dec
 * ... ожидание : Dec
 * --- deque.size=6 ---
 * ... ожидание : Dec
 * 	remove tail: April
 * --- deque.size=5 ---
 * Добавление в очередь : Dec
 * --- deque.size=6 ---
 * Извлечение из map : Feb
 * ... ожидание : Feb
 * --- deque.size=6 ---
 * ... ожидание : Feb
 * 	remove tail: March
 * --- deque.size=5 ---
 * Добавление в очередь : Feb
 * --- deque.size=6 ---
 * Извлечение из map : Jan
 * ... ожидание : Jan
 * --- deque.size=6 ---
 * ... ожидание : Jan
 * 	remove tail: July
 * --- deque.size=5 ---
 * Добавление в очередь : Jan
 * --- deque.size=6 ---
 * Извлечение из map : Jul
 * ... ожидание : Jul
 * --- deque.size=6 ---
 * ... ожидание : Jul
 * 	remove tail: June
 * --- deque.size=5 ---
 * Добавление в очередь : Jul
 * --- deque.size=6 ---
 * Извлечение из map : Jun
 * ... ожидание : Jun
 * --- deque.size=6 ---
 * ... ожидание : Jun
 * 	remove tail: Apr
 * --- deque.size=5 ---
 * Добавление в очередь : Jun
 * --- deque.size=6 ---
 * Извлечение из map : Mar
 * ... ожидание : Mar
 * 	remove tail: Aug
 * --- deque.size=5 ---
 * Добавление в очередь : Mar
 * --- deque.size=6 ---
 * Извлечение из map : May
 * ... ожидание : May
 * --- deque.size=6 ---
 * ... ожидание : May
 * 	remove tail: Dec
 * --- deque.size=5 ---
 * Добавление в очередь : May
 * --- deque.size=6 ---
 * Извлечение из map : Nov
 * ... ожидание : Nov
 * --- deque.size=6 ---
 * ... ожидание : Nov
 * 	remove tail: Feb
 * --- deque.size=5 ---
 * Добавление в очередь : Nov
 * --- deque.size=6 ---
 * Извлечение из map : Oct
 * ... ожидание : Oct
 * --- deque.size=6 ---
 * ... ожидание : Oct
 * 	remove tail: Jan
 * --- deque.size=5 ---
 * Добавление в очередь : Oct
 * --- deque.size=6 ---
 * Извлечение из map : Sep
 * ... ожидание : Sep
 * --- deque.size=6 ---
 * ... ожидание : Sep
 * 	remove tail: Jul
 * --- deque.size=5 ---
 * Добавление в очередь : Sep
 * --- deque.size=6 ---
 * 	remove tail: Jun
 * 	remove head: Sep
 * 	remove tail: Mar
 * 	remove head: Oct
 * 	remove tail: May
 * 	remove head: Nov
 * 	remove tail: null
 * 	remove tail: null
 *
 * Process finished with exit code 0
 */
