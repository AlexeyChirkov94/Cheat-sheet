package ru.chirkov.cheat.sheet.multithreading.queue.blocking;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * Следующий пример демонстрирует применение очереди LinkedTransferQueue.
 * В примере содаются два потока, работающие с очередью TransferQueue<String>.
 * Поток производитель Producer размещает элемент в очереди с использованием метода transfer.
 * Поток потребитель Consumer извлекает элементы из очереди и выводит их в консоль.
 * Перед получением элемента из очереди Consumer делает небольшую задержку, по которой можно оценить, кто кого ожидает,
 * т.е. какой поток блокируется очередью. Перед каждой операции с очередью и после выполнения операции потоки выводят
 * соответствующие сообщения в консоль с указанием времени.
 */
public class TransferQueueExample  {
    private static TransferQueue<String> queue = null;
    private static String WAIT_producer = "Producer waiting to transfer : ";
    private static String TRANSFERED    = "Producer transfered :"          ;
    private static String WAIT_consumer = "Consumer waiting to consumed : ";
    private static String CONSUMED      = "Consumer consumed : "           ;
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static void main(String args[]) throws InterruptedException {
        queue = new LinkedTransferQueue<>();
        Thread producer = new Thread(TransferQueueExample::producer);
        Thread consumer = new Thread(TransferQueueExample::consumer);
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

    private static void producer() {
        for(int i = 0; i < 2; i++){
            try{
                printMessage(WAIT_producer + i);
                queue.transfer("'object for trans №" + i + "'");
                printMessage(TRANSFERED + i + "\n");
            } catch(Exception e){}
        }

    }

    private static void consumer() {
        for(int i = 0; i < 2; i++){
            try{
                Thread.sleep(2000);
                printMessage(WAIT_consumer + i);
                String element = queue.take();
                printMessage(CONSUMED + element);
            }catch(Exception e){}
        }
    }

    private static void printMessage(final String msg)  {
        System.out.println(sdf.format(new Date()) + " " + msg);
    }

}

/**
 * console output:
 *
 * 18:24:04 Producer waiting to transfer : 0
 * 18:24:06 Consumer waiting to consumed : 0
 * 18:24:06 Producer transfered :0
 *
 * 18:24:06 Producer waiting to transfer : 1
 * 18:24:06 Consumer consumed : 'object for trans №0'
 * 18:24:08 Consumer waiting to consumed : 1
 * 18:24:08 Consumer consumed : 'object for trans №1'
 * 18:24:08 Producer transfered :1
 *
 *
 * Process finished with exit code 0
 */
