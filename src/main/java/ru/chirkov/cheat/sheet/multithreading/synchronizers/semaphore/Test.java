package ru.chirkov.cheat.sheet.multithreading.synchronizers.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Test {
    public static void main(String[] args) {
        Connection connection = Connection.getConnection();
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        for (int i = 0; i < 200; i++) {
            executorService.submit(() -> {
                try {
                    connection.workWithLimit();
                } catch (InterruptedException e) {}
            });
        }
        executorService.shutdown();
    }
}

class Connection {
    private static Connection connection = new Connection();
    private static Semaphore semaphore = new Semaphore(10);
    private int connectionCount;

    private Connection (){} //singleton
    public static Connection getConnection() {
        return connection;
    }

    public void workWithLimit() throws InterruptedException {
        semaphore.acquire(); //ждем пока появится разрешение, отработают первые 10 соединений
        try {
            workNoLimit();
        } finally {
            semaphore.release(); // сообщаем о том что соедниения отработали
            semaphore.availablePermits();
        }
    }

    public void workNoLimit() throws InterruptedException {
        synchronized (this){
            getConnection();
            connectionCount++; //открыли соединение
        }
        System.out.println("connection Count = " + connectionCount);
        Thread.sleep(5000); // полезная работа
        synchronized (this){
            connectionCount--; //закрыли соединение
        }
    }

}

/**
 * вывод в консоль:
 *
 * connection Count = 2
 * connection Count = 10
 * connection Count = 5
 * connection Count = 1
 * connection Count = 4
 * connection Count = 9
 * connection Count = 7
 * connection Count = 8
 * connection Count = 3
 * connection Count = 6
 * connection Count = 2
 * connection Count = 10
 * connection Count = 9
 * connection Count = 7
 * connection Count = 8
 * connection Count = 6
 * connection Count = 5
 * connection Count = 4
 * connection Count = 3
 * connection Count = 1
 * ...
 */