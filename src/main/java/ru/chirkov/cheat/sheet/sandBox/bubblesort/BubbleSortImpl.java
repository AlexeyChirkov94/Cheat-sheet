package ru.chirkov.cheat.sheet.sandBox.bubblesort;

public class BubbleSortImpl {

    public int[] sort(int [] massive){
        boolean haveSorted = false;
        int buffer;

        while (!haveSorted){
            haveSorted = true;
            for (int i = 0; i < massive.length - 1; i++){
                if (massive[i] > massive[i+1]) {
                    haveSorted = false;

                    buffer = massive[i];
                    massive[i] = massive[i+1];
                    massive[i+1] = buffer;
                }
            }
        }


        return massive;
    }
}
