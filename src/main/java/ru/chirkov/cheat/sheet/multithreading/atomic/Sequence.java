package ru.chirkov.cheat.sheet.multithreading.atomic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Для тестирования генератора последовательности SequenceGenerator используем класс Sequence, реализующий интерфейс Runnable.
 * В качестве параметра конструктор класса получает идентификатор потока id, размер последовательности count и генератор
 * последовательности sg. В методе run в цикле с незначительными задержками формируется последовательность чисел sequence.
 * После завершения цикла значения последовательности «выводятся» в консоль методом printSequence.
 */
class Sequence implements Runnable {
    Thread thread;
    int id;    // просто идентификатор потока
    int count; // кол-во элементов в желаемой последовательности
    SequenceGenerator sequenceGenerator;
    List<BigInteger> sequence = new ArrayList<>();
    boolean printed = false;

    Sequence(final int id, final int count, SequenceGenerator sequenceGenerator) {
        this.count = count;
        this.id = id;
        this.sequenceGenerator = sequenceGenerator;

        thread = new Thread(this);
        System.out.println("Создан поток " + id);
        thread.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < count; i++) {
                sequence.add(sequenceGenerator.getNextValue());
                Thread.sleep((long) ((Math.random()*2 + 1)*30));
            }
        } catch (InterruptedException e) {
            System.out.println("Поток " + id + " прерван");
        }
        System.out.println("Поток " + id + " завершён; ");
        printSequence();
    }

    public void printSequence() {
        if (printed) return;

        String tmp = "[";
        for (int i = 0; i < sequence.size(); i++) {
            if (i > 0) tmp += ", ";
            String nb = String.valueOf(sequence.get(i));
            while (nb.length() < 9)
                nb = " " + nb;
            tmp += nb;
        }
        tmp += "]";
        System.out.println("Последовательность потока " + id + " : " + tmp);
        printed = true;
    }
}
