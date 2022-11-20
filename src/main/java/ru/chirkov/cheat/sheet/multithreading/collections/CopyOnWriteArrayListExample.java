package ru.chirkov.cheat.sheet.multithreading.collections;

import java.util.Arrays;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * В примере формируется набор данных data, на основании которого создаются 2 коллекции.
 * 1ая потокобезопасная коллекция copyOnWriteArrayList типа CopyOnWriteArrayList
 * 2ая не потокобезопасная коллекция arrayList типа ArrayList
 *
 * При работе с коллекцией copyOnWriteArrayList:
 * Данные коллекции с помощью итератора выводятся в консоль два раза.
 * В первом цикле в коллекцию вносятся изменения (но мы не видим изменений в консоле,
 * т.к. итератор работает с копией колекции котрая была на момент создания итератора)
 * Во втором цикле данные выводятся без изменений (но вы видим изменений вмесенные в певом цилке).
 *
 * При работе с коллекцией arrayList:
 * Ислючение выбрасивает итератор после изменения колекции по которой идет перебор
 */
public class CopyOnWriteArrayListExample {

    public static void main(String args[]) {
        List<String> data = Arrays.asList("Java", "J2EE", "J2SE", "Collection", "Concurrent");

        List<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>(data);
        List<String> arrayList = new ArrayList<>(data);


        System.out.println("ЦИКЛ с изменением, CopyOnWriteArrayList");
        printCollection(copyOnWriteArrayList,true); //тут колеклция изменилась но вывелась первоначальная, т.к. итераторр работает по снимку на момент создания итератора
        System.out.println("ЦИКЛ без изменением, CopyOnWriteArrayList");
        printCollection(copyOnWriteArrayList, false); // тут мы не производили изменений но колекция вывелась обновленая после предудущих измененений

        System.out.println("\nЦИКЛ с изменением, ArrayList");
        printCollection(arrayList,true); // упал т.к. итератор вызывает исключение при обращении к следующему объекту, если набор изменился.
    }

    private static void printCollection(List<String> list, boolean change) {
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String element = iterator.next();
            System.out.printf("  %s %n", element);
            if (change && element.equals("Collection")) {
                list.add("Новая строка");
                list.remove(element);
            }
        }
    }

}

/**
 * вывод в консоль:
 *
 * ЦИКЛ с изменением, CopyOnWriteArrayList
 *   Java
 *   J2EE
 *   J2SE
 *   Collection
 *   Concurrent
 * ЦИКЛ без изменением, CopyOnWriteArrayList
 *   Java
 *   J2EE
 *   J2SE
 *   Concurrent
 *   Новая строка
 *
 * ЦИКЛ с изменением, ArrayList
 *   Java
 *   J2EE
 *   J2SE
 *   Collection
 * Exception in thread "main" java.util.ConcurrentModificationException
 * 	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1043)
 * 	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:997)
 * 	at ru.chirkov.cheat.sheet.multithreading.collections.CopyOnWriteArrayListExample.printCollection(CopyOnWriteArrayListExample.java:30)
 * 	at ru.chirkov.cheat.sheet.multithreading.collections.CopyOnWriteArrayListExample.main(CopyOnWriteArrayListExample.java:24)
 */
