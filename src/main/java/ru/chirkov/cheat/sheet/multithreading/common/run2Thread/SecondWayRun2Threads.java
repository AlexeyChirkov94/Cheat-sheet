package ru.chirkov.cheat.sheet.multithreading.common.run2Thread;


class AffableThread extends Thread {

    @Override
    public void run() {	//Этот метод будет выполнен в побочном потоке
        System.out.println("Привет из побочного потока!");
    }

}

public class SecondWayRun2Threads {

    static AffableThread mSecondThread;

    public static void main(String[] args) {
        mSecondThread = new AffableThread();	//Создание потока
        mSecondThread.start();					//Запуск потока

        System.out.println("Главный поток завершён...");
    }
}

/**
 * вывод в консоль:
 *
 * Главный поток завершён...
 * Привет из побочного потока!
 *
 * Process finished with exit code 0
 */