package ru.chirkov.cheat.sheet.algorithms.and.data.structures.recusion;

public class MergeSort {

    public static void main(String[] args)
    {
        int maxSize = 100; // Размер массива
        DArray arr =  new DArray(maxSize); // Ссылка на массив
        arr.insert(64); // Вставка элементов
        arr.insert(21);
        arr.insert(33);
        arr.insert(70);
        arr.insert(12);
        arr.insert(85);
        arr.insert(54);
        arr.insert(43);
        arr.insert(44);
        arr.insert(3);
        arr.insert(99);
        arr.insert(0);
        arr.insert(108);
        arr.insert(36);
        arr.display(); // Вывод содержимого массива
        arr.mergeSort(); // Сортировка слиянием
        arr.display(); // Повторный вывод
    }

}

class DArray  {

    private long[] theArray; // Ссылка на массив theArray
    private int nElems; // Количество элементов данных

    public DArray(int max) { // Конструктор
        theArray = new long[max]; // Создание массива
        nElems = 0;
    }


    public void insert(long value) { // Занесение элемента в массив
        theArray[nElems] = value; // Вставка элемента
        nElems++; // Увеличение размера
    }

    public void display() { // Вывод содержимого массива
        for(int j=0; j<nElems; j++) // Для каждого элемента
            System.out.print(theArray[j] + " "); // Вывод
        System.out.println("");
    }

    public void mergeSort() {
        long[] workSpace = new long[nElems];
        recMergeSort(workSpace, 0, nElems-1);
    }

    private void recMergeSort(long[] workSpace, int lowerBound, int upperBound) {
        if(lowerBound == upperBound) return; // Если только один элемент, сортировка не требуется.
        else {
            int mid = (lowerBound+upperBound) / 2; // Поиск середины
            recMergeSort(workSpace, lowerBound, mid); // Сортировка нижней половины
            recMergeSort(workSpace, mid+1, upperBound); // Сортировка верхней половины
            merge(workSpace, lowerBound, mid+1, upperBound); // Слияние
        }
    }

    private void merge(long[] workSpace, int lowPtr, int highPtr, int upperBound) {
        int j = 0; // Индекс в рабочей области
        int lowerBound = lowPtr;
        int mid = highPtr-1;
        int n = upperBound-lowerBound+1; // Количество элементов
        while(lowPtr <= mid && highPtr <= upperBound)
            if( theArray[lowPtr] < theArray[highPtr] )
                workSpace[j++] = theArray[lowPtr++];
            else workSpace[j++] = theArray[highPtr++];
        while(lowPtr <= mid)
            workSpace[j++] = theArray[lowPtr++];
        while(highPtr <= upperBound)
            workSpace[j++] = theArray[highPtr++];
        for(j=0; j<n; j++)
            theArray[lowerBound+j] = workSpace[j];
    }

}
