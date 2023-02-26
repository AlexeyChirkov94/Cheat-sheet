package ru.chirkov.cheat.sheet.multithreading.common.interruption;

public class InterruptionStatusExample {

    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread( ()-> {
            for (int i = 0; i < 30_000_000; i++){
                System.out.println("circle for run");
                if (!Thread.currentThread().isInterrupted()) {
                    System.out.println("for start  i = " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("catch block, InterruptedException was!");
                        System.out.println("isInterrupted = " + Thread.currentThread().isInterrupted()); //не смотря на то что поток прерван статус переваности - false
                        Thread.currentThread().interrupt(); // восстанавливаем статус прерваности (закоментируй посмотри что будет если не восставноить)
                        System.out.println("isInterrupted = " + Thread.currentThread().isInterrupted()); // повреяем восстановление статуса прерваности
                    }
                    System.out.println("for finish i = " + i);
                } else break;
            }

        });

        myThread.start();
        Thread.sleep(300);
        myThread.interrupt();
        myThread.join();
    }

}

/**
 * вывод в консоль:
 *
 * circle for run
 * for start  i = 0
 * for finish i = 0
 * circle for run
 * for start  i = 1
 * for finish i = 1
 * circle for run
 * for start  i = 2
 * catch block, InterruptedException was!
 * isInterrupted = false
 * isInterrupted = true
 * for finish i = 2
 * circle for run
 *
 * Process finished with exit code 0
 */
