package ru.chirkov.cheat.sheet.algorithms.and.data.structures.massive;

import java.util.HashSet;
import java.util.Set;

public class HighArray {

    private long[] data; // Ссылка на массив a
    private int nElems; // Количество элементов в массиве

    public HighArray(int max) {
        data = new long[max];
        nElems = 0;
    }

    public boolean find(long searchKey) { // Поиск заданного значения
        int j;
        for(j=0; j<nElems; j++) // Для каждого элемента
            if(data[j] == searchKey) // Значение найдено?
                break; // Да - выход из цикла
        if(j == nElems) // Достигнут последний элемент?
            return false; // Да
        else
            return true; // Нет
    }

    public void insert(long value) {// Вставка элемента в массив
        data[nElems] = value; // Собственно вставка
        nElems++; // Увеличение размера
    }

    public boolean delete(long value) {
        int j;
        for(j=0; j<nElems; j++) // Поиск заданного значения
            if(value == data[j]) break;
        if(j==nElems) // Найти не удалось
            return false;
        else { // Значение найдено
            for(int k=j; k<nElems; k++) // Сдвиг последующих элементов
                data[k] = data[k+1];
            nElems--;
            return true;
        }
    }

    public void display() {
        for(int j=0; j<nElems; j++)
            System.out.print(data[j] + " ");
        System.out.println("");
    }

    public long getMax(){
        long maxElem = -1;
        for (int i = 0; i<nElems; i++){
            long newCandidate = data[i];
            if (newCandidate > maxElem) maxElem = newCandidate;
        }
        return maxElem;
    }

    public long removeMax() {
        long maxElem = -1;
        int indexOfMaxElem = -1;
        for (int i = 0; i<nElems; i++){
            long newCandidate = data[i];
            if (newCandidate > maxElem) {
                maxElem = newCandidate;
                indexOfMaxElem = i;
            }
        }
        if (maxElem == -1) return -1;

        for(int k=indexOfMaxElem; k<nElems; k++) // Сдвиг последующих элементов
            data[k] = data[k+1];
        nElems--;

        return maxElem;
    }

    public void selectionSort(){
        long[] sorted = new long[data.length];
        int nElemSaved = nElems;

        while (nElems != 0)
            sorted[nElems-1] = removeMax();

        data = sorted;
        nElems = nElemSaved;
    }

    public void noDups(){
        Set<Long> longSet = new HashSet<>();
        HighArray noDupsArray = new HighArray(data.length);

        for (int i = 0; i < nElems; i++)
            longSet.add(data[i]);

        for (Long item : longSet)
            noDupsArray.insert(item);

        data = noDupsArray.data;
        nElems = noDupsArray.nElems;

    }

}

class HighArrayApp {

    public static void main(String[] args) {
        int maxSize = 100; // Размер массива
        HighArray arr = new HighArray(maxSize);

        arr.insert(77); // Вставка 10 элементов
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(55);
        arr.insert(88);
        arr.insert(11);
        arr.insert(1);
        arr.insert(66);
        arr.insert(33);
        arr.display(); // Вывод элементов
        System.out.println("max elem = " + arr.getMax());
        System.out.println("removeMax run");
        arr.removeMax();
        arr.display(); // Вывод элементов
        System.out.println("max elem = " + arr.getMax() + "\n");
        System.out.println("runSorted");
        arr.selectionSort();
        arr.display();

        System.out.println("run no dups");
        arr.noDups();
        arr.display();

        int searchKey = 35; // Поиск элемента
        if( arr.find(searchKey) )
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't find " + searchKey);
        arr.delete(00); // Удаление трех элементов
        arr.delete(55);
        arr.delete(99);
        arr.display(); // Повторный вывод
    }



}
