package ru.chirkov.cheat.sheet.algorithms.and.data.structures.simple.sort;

import java.util.Random;

public class ArrayBub {

    private long[] data;
    private int nElems;

    public ArrayBub(int max){ // Конструктор
        data = new long[max]; // Создание массива
        nElems = 0; // Пока нет ни одного элемента
    }

    public void insert(long value){ // Вставка элемента в массив
        data[nElems] = value; // Собственно вставка
        nElems++; // Увеличение размера
    }

    public void display(){ // Вывод содержимого массива
        for(int j=0; j<nElems; j++) // Для каждого элемента
            System.out.print(data[j] + " "); // Вывод
        System.out.println("");
    }

    public int size(){
        return nElems;
    }

    public void bubbleSort() {
        int out, in;
        for(out=nElems-1; out>1; out--) // Внешний цикл (обратный)
            for(in=0; in<out; in++) // Внутренний цикл (прямой)
                if(data[in] > data[in+1]) // Порядок нарушен?
                    swap(in, in+1); // Поменять местами
    }

    private void swap(int one, int two) {
        long temp = data[one];
        data[one] = data[two];
        data[two] = temp;
    }
}

class BubbleSortApp {

    public static void main(String[] args) {
        ArrayBub arr = new ArrayBub(1_000); // Создание массива
        fillArr(arr, 10);

        arr.display(); // Вывод элементов
        long start = System.currentTimeMillis();
        arr.bubbleSort(); // Пузырьковая сортировка элементов
        long end = System.currentTimeMillis();
        System.out.println("bubble sorting: dur = " + (end - start) + ", array size = " + arr.size());
        arr.display(); // Повторный вывод
    }

    private static void fillArr(ArrayBub arr, int size){
        Random rand = new Random();
        for (int i = 0; i < size; i++)
            arr.insert(rand.nextInt(100));
    }

}
