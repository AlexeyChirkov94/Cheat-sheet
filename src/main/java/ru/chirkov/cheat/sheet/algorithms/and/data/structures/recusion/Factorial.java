package ru.chirkov.cheat.sheet.algorithms.and.data.structures.recusion;

public class Factorial {

    public static void main(String[] args) {

        System.out.println("factorial of 0 = " + factorial(0));
        System.out.println("factorial of 1 = " + factorial(1));
        System.out.println("factorial of 2 = " + factorial(2));
        System.out.println("factorial of 3 = " + factorial(3));
        System.out.println("factorial of 4 = " + factorial(4));
        System.out.println("factorial of 5 = " + factorial(5));
        System.out.println("factorial of 6 = " + factorial(6));
        System.out.println("factorial of 7 = " + factorial(7));
        System.out.println("factorial of 8 = " + factorial(8));

    }

    public static int factorial(int number){
        if (number == 0) return 1;
        else return factorial(number - 1) * number;
    }

}
