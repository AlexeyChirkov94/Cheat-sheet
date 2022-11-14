package ru.chirkov.cheat.sheet.multithreading.atomic;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Для работы в многопоточной среде без блокировок используем атомарную ссылку AtomicReference, которая обеспечит хранение
 * целочисленного значения типа java.math.BigInteger. Метод getNextValue возвращает текущее значение и подготвалвивает следующее.
 * Как getAndAdd класса AtomicLong
 * переменная next вычисляет следующее значение.
 * Метод compareAndSet атомарного класса element обеспечивает сохранение нового значения, если текущее не изменилось.
 * Таким образом, метод next возвращает текущее значение и увеличивает его в 2 раза.
 */
public class SequenceGenerator {

    private static BigInteger MULTIPLIER = BigInteger.valueOf(2);
    private AtomicReference<BigInteger> element;

    public SequenceGenerator() {
        element = new AtomicReference<>(BigInteger.ONE);
    }

    public BigInteger getNextValue() {
        BigInteger value;
        BigInteger next;
        do {
            value = element.get();
            next = value.multiply(MULTIPLIER);
        } while (!element.compareAndSet(value, next));
        return value;
    }

}
