package ru.chirkov.cheat.sheet.algorithms.and.data.structures.recusion;

import java.util.function.Function;

/**
 * Говорят, пифагорейцы — группа древнегреческих математиков, работавших под
 * началом Пифагора (автора знаменитой теоремы) — ощущали мистическую связь
 * с числовыми рядами вида 1, 3, 6, 10, 15, 21, … . Сможете ли вы угадать следующее
 * число в этом ряду?
 * N-е число ряда получается прибавлением n к предыдущему числу. Таким обра-
 * зом, чтобы получить второе число, мы увеличиваем первое (1) на 2; 1 + 2 = 3. Третье
 * число получается увеличением второго (3) на 3; 3 + 3 = 6 и т. д.
 */
public class TriangleNumber {

    public static void main(String[] args) {

        System.out.println("Cycle: ");
        testTriangleFunction(TriangleNumber::getTriangleNumberCycle);

        System.out.println("Recursion: ");
        testTriangleFunction(TriangleNumber::getTriangleNumberRecursion);

    }


    public static int getTriangleNumberCycle(int serialNumber){
        int result = 0;

        for (int i = 1; i < serialNumber + 1; i++ ) {
            result = result + i;
        }
        return result;
    }

    public static int getTriangleNumberRecursion(int n) {
        if (n == 1) return 1;
        else return(n + getTriangleNumberRecursion(n-1));
    }

    public static void testTriangleFunction(Function<Integer, Integer> getTriangleFunction) {
        System.out.println("triangle number for place 1 is " + getTriangleFunction.apply(1));
        System.out.println("triangle number for place 2 is " + getTriangleFunction.apply(2));
        System.out.println("triangle number for place 3 is " + getTriangleFunction.apply(3));
        System.out.println("triangle number for place 4 is " + getTriangleFunction.apply(4));
        System.out.println("triangle number for place 5 is " + getTriangleFunction.apply(5));
        System.out.println("triangle number for place 6 is " + getTriangleFunction.apply(6));
        System.out.println("triangle number for place 7 is " + getTriangleFunction.apply(7));
        System.out.println("triangle number for place 8 is " + getTriangleFunction.apply(8));
        System.out.println("triangle number for place 9 is " + getTriangleFunction.apply(9));
        System.out.println("triangle number for place 10 is " + getTriangleFunction.apply(10));
    }

}
