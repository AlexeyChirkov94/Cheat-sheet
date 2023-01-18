package ru.chirkov.cheat.sheet.multithreading.sandbox;

/**
 * Пример демострирует работу обертки ThreadLocal. Каждый поток получает то значение которое сам и положил,
 * не мотря на то, что переменная threadLocal общая.
 */
public class ThreadLocalExample {

    private static ThreadLocal<TestDTO> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> method(1, "one"));
        Thread thread2 = new Thread(() -> method(2, "two"));
        Thread thread3 = new Thread(() -> method(3, "three"));

        thread1.start();
        Thread.sleep(600);
        thread2.start();
        Thread.sleep(600);
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

    }

    private static void method(Integer id, String name) {
        System.out.println("thread " + id + " start");
        TestDTO testDTO = new TestDTO(id, name);
        threadLocal.set(testDTO);
        System.out.println("thread " + id + " put var into threadLocal");
        System.out.println("thread " + id + " go to sleep");
        Thread.yield();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignore) {}
        System.out.println("thread " + id + " wake up");
        System.out.println("thread " + id + " var is " + threadLocal.get());
    }

    static class TestDTO{

        int id;
        String name;

        public TestDTO(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "TestDTO{" + "id=" + id + ", name='" + name + '\'' + '}';
        }
    }

}

/**
 * вывод в консоль:
 *
 * thread 1 start
 * thread 1 put var into threadLocal
 * thread 1 go to sleep
 * thread 2 start
 * thread 2 put var into threadLocal
 * thread 2 go to sleep
 * thread 1 wake up
 * thread 1 var is TestDTO{id=1, name='one'}
 * thread 3 start
 * thread 3 put var into threadLocal
 * thread 3 go to sleep
 * thread 2 wake up
 * thread 2 var is TestDTO{id=2, name='two'}
 * thread 3 wake up
 * thread 3 var is TestDTO{id=3, name='three'}
 *
 * Process finished with exit code 0
 */
