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
