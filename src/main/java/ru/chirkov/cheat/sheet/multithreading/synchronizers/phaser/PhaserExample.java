package ru.chirkov.cheat.sheet.multithreading.synchronizers.phaser;

import java.util.ArrayList;
import java.util.concurrent.Phaser;

/**
 * https://java-online.ru/concurrent-synchronizers.xhtml#phaser
 *
 * Phaser (фазировщик), как и CyclicBarrier, является реализацией объекта синхронизации типа «Барьер» (CyclicBarrier).
 * В отличии от CyclicBarrier, Phaser предоставляет больше гибкости.
 *
 * Во примере несколько потоков реализуют перевозку пассажиров городским транспортом.
 * Пассажиры ожидают транспорт на разных остановках. Транспорт, останавливаясь на остановках, одних пассажиров «сажает»,
 * других «высаживает».
 */
public class PhaserExample {
    private static Phaser PHASER;

    public static void main(String[] args) throws InterruptedException {
        PHASER = new Phaser(1); // Регистрация объекта синхронизации
        ArrayList<Passenger> passengers = new ArrayList<>();

        for (int i = 1; i < 5; i++) {  // Формирование массива пассажиров
            if ((int) (Math.random() * 2) > 0) // Этот пассажир проезжает одну станцию
                passengers.add(new Passenger(10 + i, i, i + 1));
            if ((int) (Math.random() * 2) > 0) // Этот пассажир едет до конечной
                passengers.add(new Passenger(20 + i, i, 5));
        }
        for (int i = 0; i < 7; i++) { // Фазы 0 и 6 - конечные станции, 1...5 - промежуточные станции
            switch (i) {
                case 0:
                    System.out.println("Метро вышло из тупика");
                    PHASER.arrive(); // Нулевая фаза, участников нет, сообщаем файзеру о завершении фазы
                    break;
                case 6:
                    System.out.println("Метро ушло в тупик");
                    PHASER.arriveAndDeregister(); // Завершаем синхронизацию
                    break;
                default:
                    int currentStation = PHASER.getPhase();
                    System.out.println("Станция " + currentStation);

                    // Проверка наличия пассажиров на станции
                    for (Passenger pass : passengers)
                        if (pass.departure == currentStation) {
                            PHASER.register(); // Регистрация одного новго участника
                            new Thread(pass).start();
                        }
                    System.out.println("     открытие дверей ");
                    PHASER.arriveAndAwaitAdvance();  // Phaser ожидает завершения фазы всеми участниками
                    System.out.println("     закрытие дверей ");
            }
        }
    }

    public static class Passenger implements Runnable {
        int id;
        int departure;
        int destination;
        public Passenger(int id, int departure, int destination) {
            this.id = id;
            this.departure = departure;
            this.destination = destination;
            System.out.println(this + " ждёт на станции " + departure);
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500); //что-бы двери успели открыться
                System.out.println("    " + this + " вошел в вагон"); //входим

                while (PHASER.getPhase() < destination)  // ждем станции назначения
                    PHASER.arriveAndAwaitAdvance(); // Заявляем что завершили текущую фазу (говорим, "мы тут не выходим, нас не ждиЭ)

                Thread.sleep(500); //что-бы двери успели открыться
                System.out.println("    " + this + " вышел из вагона "); //выходим

                PHASER.arriveAndDeregister();  // Сходим по старнии, и отменяем регистрацию (регистрацию на работе в PHASER (больше нас ждать не нужно))
            } catch (InterruptedException e) {}
        }
        @Override
        public String toString() { return "Пассажир " + id + " {" + departure + " -> " + destination + '}'; }
    }
}
