package ru.chirkov.cheat.sheet.sandBox.teldaTask;

public  class SomeClass {

    public static void main(String[] args) {
        int a = 1;
        doSome(a);
        System.out.println(a);

        Object b = new Object();
        Object c = b;
//        Object c = new Object();
//        doSomeRef(b);
        System.out.println(b == c);
    }

    private static void doSome(int b) {
        b = b++;
    }

    private static void doSomeRef(Object o) {
        o = new Object();
    }
}
