package ru.chirkov.cheat.sheet.algorithms.and.data.structures.recusion;

public class Tower {

    private static int nDisks = 4;
    private static long countOfSteps = 0; // равно 2^nDisk - 1
    public static void main(String[] args) {
        doTowers(nDisks, 'A', 'B', 'C');
        System.out.println("count of disk = " + nDisks + ", count of steps = " + countOfSteps);

    }


    public static void doTowers(int topN, char from, char inter, char to) {
        if(topN==1) {
            countOfSteps++;
            System.out.println("Disk 1 from " + from + " to "+ to);
        }
        else {
            doTowers(topN-1, from, to, inter); // from-->inter
            System.out.println("Disk " + topN + " from " + from + " to "+ to);
            countOfSteps++;
            doTowers(topN-1, inter, from, to); // inter-->to
        }
    }

}
