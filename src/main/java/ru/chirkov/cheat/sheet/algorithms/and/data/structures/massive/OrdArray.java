package ru.chirkov.cheat.sheet.algorithms.and.data.structures.massive;

public class OrdArray {

    private long[] data; // Ссылка на массив a
    private int nElems; // Количество элементов данных

    public OrdArray(int max) {
        data = new long[max];
        nElems = 0;
    }

    public int size() {
        return nElems;
    }

    public int find(long searchValue) {
        return binSearch(searchValue);
    }

    public long get(int index){
        return data[index];
    }

    public void insert(long value) // Вставка элемента в массив
    {
        int j;  //место для вставки

        int lowBound = 0;
        int upperBound = nElems == 0 ? 0 : nElems - 1;
        if (value <= data[lowBound]) j = 0;
        else if (value >= data[upperBound]) j = nElems;
        else {
            while (true){
                int curIndex = (lowBound + upperBound) / 2;
                long curValue = data[curIndex];
                if (curValue == value) {
                    j = curIndex;
                    break;
                }
                if (lowBound + 1 == upperBound){
                    j = lowBound + 1;
                    break;
                }
                if (curValue > value)
                    upperBound = curIndex;
                else
                    lowBound = curIndex;
            }
        }
        for(int k=nElems; k>j; k--) // Перемещение последующих элементов
            data[k] = data[k-1];
        data[j] = value; // Вставка
        nElems++; // Увеличение размера
    }

    public boolean delete(long value) {
        int j = binSearch(value);
        if (j == -1) // Найти не удалось
            return false;
        else { // Элемент найден
            for (int k = j; k < nElems; k++) // Перемещение последующих элементов
                data[k] = data[k + 1];
            if (nElems != 0) nElems--; // Уменьшение размера
            return true;
        }
    }

    public void display(){
        for(int j=0; j<nElems; j++)
            System.out.print(data[j] + " ");
        System.out.println("");
    }

    public OrdArray merge(OrdArray ordArray){

        int thisSize = nElems;
        int overSize = ordArray.size();

        if (thisSize + overSize > data.length) throw new RuntimeException("Суммарное кол-во элементов превышает размер массива");

        if (thisSize >= overSize){
            for (int i = 0; i < overSize; i++)
                insert(ordArray.get(i));
            return this;
        } else {
            for (int i = 0; i < thisSize; i++)
                ordArray.insert(this.get(i));
            return ordArray;
        }

    }

    private int binSearch(long searchValue){
        int lowerBound = 0;
        int upperBound = nElems == 0 ? 0 : nElems - 1;
        int curIn;

        while(true) {
            curIn = (lowerBound + upperBound ) / 2;
            if(data[curIn]==searchValue)
                return curIn; // Элемент найден
            else if(lowerBound > upperBound)
                return -1; // Элемент не найден
            else { // Деление диапазона
                if(data[curIn] < searchValue)
                    lowerBound = curIn + 1; // В верхней половине
                else
                    upperBound = curIn - 1; // В нижней половине
            }
        }
    }

}


class OrderedApp {
    public static void main(String[] args) {

        int maxSize = 25; // Размер массива
        OrdArray arr = new OrdArray(maxSize); // Создание массива
        arr.insert(77); // Вставка 10 элементов
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);
        int searchKey = 55; // Поиск элемента
        if( arr.find(searchKey) != -1 )
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't find " + searchKey);

        arr.display(); // Вывод содержимого
        System.out.println("Run 3 deleting");
        arr.delete(00); // Удаление трех элементов
        arr.delete(55);
        arr.delete(99);
        arr.display(); // Повторный вывод

        OrdArray arr2 = new OrdArray(maxSize); // Создание массива
        arr2.insert(77); // Вставка 10 элементов
        arr2.insert(99);
        arr2.insert(44);
        arr2.insert(55);
        arr2.insert(22);
        arr2.insert(88);
        arr2.insert(11);
        arr2.insert(00);
        arr2.insert(66);
        arr2.insert(33);

        System.out.println("mergind");
        OrdArray merged = arr.merge(arr2);
        merged.display();
    }

}
