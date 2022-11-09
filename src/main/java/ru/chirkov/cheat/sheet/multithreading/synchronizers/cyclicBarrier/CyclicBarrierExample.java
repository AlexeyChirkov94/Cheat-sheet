package ru.chirkov.cheat.sheet.multithreading.synchronizers.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * https://java-online.ru/concurrent-synchronizers.xhtml#cyclicbarrier
 *
 * В примере организуется переправа. Паром может вместить только 3 автомобиля. Количество автомобилей 9.
 * Роль парома выполняет объект синхронизации FerryBarrier, которому в качестве второго параметра передается
 * реализующий интерфейс Runnable класс FerryBoat. Как только 3 потока достигнут барьера автоматически будет
 * запущен FerryBoat, после завершения работы которого потоки продолжают свою работу.
 */
public class CyclicBarrierExample  {

    private static CyclicBarrier FerryBarrier;
    private static final int FerryBoat_size = 3;

    public static void main(String[] args) throws InterruptedException {
        FerryBarrier = new CyclicBarrier(FerryBoat_size, new FerryBoat());

        for (int i = 1; i < 7; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(400);
        }
    }

    public static class FerryBoat implements Runnable { // Переправляющий автомобили паром
        @Override
        public void run() {
            try {
                // Задержка на переправе
                System.out.println("\nЗагрузка автомобилей");
                Thread.sleep(500);
                System.out.println("Паром переправил автомобили\n");
            } catch (InterruptedException e) {}
        }
    }

    // Класс автомобиля
    public static class Car implements Runnable {
        private int carNumber;
        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            try {
                System.out.printf("К переправе подъехал автомобиль %d\n", carNumber);
                FerryBarrier.await(); // Вызов метода await при подходе к барьеру; поток блокируется в ожидании прихода остальных потоков
                System.out.printf("Автомобиль %d продолжил движение\n", carNumber);
            } catch (Exception e) {}
        }
    }

}