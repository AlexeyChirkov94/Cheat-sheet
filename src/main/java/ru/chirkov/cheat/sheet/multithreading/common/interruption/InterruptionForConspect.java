package ru.chirkov.cheat.sheet.multithreading.common.interruption;

class MyThread extends Thread  {

    @Override
    public void run() {

        while(true) {
            if(!Thread.interrupted()) {	//Проверка прерывания
                //логика если поток не прерван
            } else {
                return; //Завершение потока если он перван
            }

            try{
                Thread.sleep(1000);		//Приостановка потока на 1 сек.
            }catch(InterruptedException e){
                return;	//Завершение потока после прерывания (прерван пока выполнялся sleep)
            }
        }

    }
}

public class InterruptionForConspect {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();	//Создание потока
        myThread.start();	//Запуск потока
        myThread.interrupt();	//Инициация завершения побочного потока
    }
}
