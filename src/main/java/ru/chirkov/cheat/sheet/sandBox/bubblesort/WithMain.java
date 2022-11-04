package ru.chirkov.cheat.sheet.sandBox.bubblesort;

import java.util.Arrays;

public class WithMain {

    public static void main(String[] args){

        StringBuffer a = new StringBuffer();
        StringBuilder b = new StringBuilder();

        BubbleSortImpl bubbleSort = new BubbleSortImpl();
        int [] mas = {11, 3, 14, 16, 7};

        int[] mas2 = bubbleSort.sort(mas);

        System.out.println(Arrays.toString(mas));
        System.out.println(Arrays.toString(mas2));
    }



}
