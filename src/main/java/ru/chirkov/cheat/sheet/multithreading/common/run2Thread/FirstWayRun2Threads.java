package ru.chirkov.cheat.sheet.multithreading.common.run2Thread;

class SomeThing	implements Runnable {	//Нечто, реализующее интерфейс Runnable(содержащее метод run())

    @Override
    public void run() {		//Этот метод будет выполняться в побочном потоке
        System.out.println("Привет из побочного потока!");
    }

}

public class FirstWayRun2Threads {			//Класс с методом main()

    public static void main(String[] args) {
        SomeThing mThing = new SomeThing(); //mThing - объект класса, реализующего интерфейс Runnable

        Thread myThready = new Thread(mThing);	//Создание потока "myThready"
        myThready.start();				//Запуск потока

        System.out.println("Главный поток завершён...");
    }
}