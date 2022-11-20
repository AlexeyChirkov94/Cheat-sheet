package ru.chirkov.cheat.sheet.multithreading.common.interruption;

class Incremenator extends Thread  {

    private volatile boolean mIsIncrement = true;

    public void changeAction() { //Меняет действие на противоположное
        mIsIncrement = !mIsIncrement;
    }

    @Override
    public void run() {

        while(true) {
            if(!Thread.interrupted()){	//Проверка прерывания
                if(mIsIncrement) ThreadInterruption.mValue++;	//Инкремент
                else ThreadInterruption.mValue--;			//Декремент

                //Вывод текущего значения переменной
                System.out.print(ThreadInterruption.mValue + " ");
            } else {
                return;        //Завершение потока
            }

            try{
                Thread.sleep(100);		//Приостановка потока на 1 сек.
            }catch(InterruptedException e){
                return;	//Завершение потока после прерывания
            }
        }

    }
}

public class ThreadInterruption {
    //Переменая, которой оперирует инкременатор
    public static int mValue = 0;

    public static void main(String[] args) {

        Incremenator mInc = new Incremenator();	//Создание потока
        System.out.print("Значение = ");

        mInc.start();	//Запуск потока
        //Троекратное изменение действия инкременатора
        //с интервалом в i*2 секунд
        for(int i = 1; i <= 3; i++) {
            try{
                Thread.sleep(i*2*100); //Ожидание в течении i*2 сек.
            }catch(InterruptedException e){}
            mInc.changeAction();	//Переключение действия
        }

        mInc.interrupt();	//Инициация завершения побочного потока
    }
}

/**
 * вывод в консоль:
 *
 * Значение = 1 2 1 0 -1 -2 -1 0 1 2 3 4
 * Process finished with exit code 0
 */