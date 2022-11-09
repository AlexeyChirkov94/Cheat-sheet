package ru.chirkov.cheat.sheet.multithreading.synchronizers.exchanger;

import java.util.concurrent.Exchanger;

/**
 * https://java-online.ru/concurrent-synchronizers.xhtml#exchanger
 *
 * В примере использования объекта синхронизации Exchanger два почтальона из пунктов А и Б отправляются в соседние
 * поселки В и Г доставить письма. Каждый из почтальонов должен доставить по письму в каждый из поселков.
 * Чтобы не делать лишний круг, они встречаются в промежуточном поселке Д и обмениваются одним письмом.
 * В результате этого каждому из почтальонов придется доставить письма только в один поселок.
 * В примере все «шаги» почтальонов фиксируются выводом соответствующих сообщений в консоль.
 */
public class ExchangerExample {

    private static Exchanger<Letter> EXCHANGER; // Обменник почтовыми письмами

    public static void main(String[] args) throws InterruptedException {
        EXCHANGER = new Exchanger<Letter>();
        // Формирование отправлений
        Letter[] posts1 = new Letter[2];
        Letter[] posts2 = new Letter[2];

        posts1[0] = new Letter("п.В - Петров"           );
        posts1[1] = new Letter("п.Г - Киса Воробьянинов");
        posts2[0] = new Letter("п.Г - Остап Бендер"     );
        posts2[1] = new Letter("п.В - Иванов"           );
        // Отправление почтальонов
        new Thread(new Postman("a","А","В", posts1)).start();
        Thread.sleep(100);
        new Thread(new Postman("б","Б","Г", posts2)).start();
    }

    public static class Postman implements Runnable {
        private String   id;
        private String   departure;
        private String   destination;
        private Letter[] letters;

        public Postman(String id, String departure, String destination, Letter[] letters) {
            this.id          = id;
            this.departure   = departure;
            this.destination = destination;
            this.letters     = letters;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Почтальон %s получил письма : %s, %s\n", id, letters[0], letters[1]);
                System.out.printf("Почтальон %s выехал из %s в %s\n", id, departure, destination);
                Thread.sleep((long) Math.random() * 5000 + 5000);
                System.out.printf("Почтальон %s приехал в пункт Д (для обмена письмами)\n", id);

                // Самоблокировка потока для обмена письмами (ожидаем прибытия второго почталона)
                letters[1] = EXCHANGER.exchange(letters[1]); // Обмен письмами

                System.out.printf("Почтальон %s получил письма для %s\n", id, destination);
                Thread.sleep(1000 + (long) Math.random() * 5000);
                System.out.printf("Почтальон %s привез в %s : %s, %s\n", id, destination, letters[0], letters[1]);
            } catch (InterruptedException e) {}
        }

    }

    public static class Letter {
        private String address;
        public Letter(final String address) {
            this.address = address;
        }
        public String toString() {
            return address;
        }
    }

}
