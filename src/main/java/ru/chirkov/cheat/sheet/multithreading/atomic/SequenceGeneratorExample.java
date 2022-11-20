package ru.chirkov.cheat.sheet.multithreading.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * В примере SequenceGeneratorExample сначала создается генератор последовательности SequenceGenerator.
 * После этого в цикле формируется массив из десяти Sequence, которые в паралелльных потоках по три раза обращаются к
 * генератору последовательсности.
 */
public class SequenceGeneratorExample {
    public static void main(String[] args) throws InterruptedException {

        SequenceGenerator sequenceGenerator = new SequenceGenerator();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 1; i < 11; i++)
            executorService.submit(new Sequence(i, 3, sequenceGenerator));

        System.out.println("\nРасчет последовательностей\n");
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("\n\nРабота потоков завершена");

    }
}

/**
 * вывод в консоль:
 *
 * Создан поток 1
 * Создан поток 2
 * Создан поток 3
 * Создан поток 4
 * Создан поток 5
 * Создан поток 6
 * Создан поток 7
 * Создан поток 8
 * Создан поток 9
 * Создан поток 10
 *
 * Расчет последовательностей
 *
 * Поток 8 завершён;
 * Поток 9 завершён;
 * Поток 7 завершён;
 * Поток 4 завершён;
 * Поток 3 завершён;
 * Поток 2 завершён;
 * Поток 5 завершён;
 * Последовательность потока 8 : [      128,      8192,  16777216]
 * Последовательность потока 5 : [       16,    131072, 134217728]
 * Последовательность потока 3 : [        2,    262144,   8388608]
 * Последовательность потока 9 : [      256,      1024,   2097152]
 * Последовательность потока 7 : [       64,      2048,    524288]
 * Последовательность потока 4 : [        8,     16384,  67108864]
 * Последовательность потока 2 : [        4,     65536,   4194304]
 * Поток 6 завершён;
 * Последовательность потока 6 : [       32,     32768, 268435456]
 * Поток 1 завершён;
 * Последовательность потока 1 : [        1,   1048576, 536870912]
 * Поток 10 завершён;
 * Последовательность потока 10 : [      512,      4096,  33554432]
 *
 *
 * Работа потоков завершена
 *
 * Process finished with exit code 0
 */
