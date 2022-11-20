package ru.chirkov.cheat.sheet.multithreading.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Следует отметить, что в этом методе мы проверяем срабатывание исключения ConcurrentModificationException,
 * связанное с работой итератора и модификацией набора во время перебора.
 * После нахождения ключа и добавления объекта в набор, цикл не прерывается.
 */
public class ConcurrentHashMapExample {

    public static void main(String[] args) {
        System.out.println("ConcurrentHashMap");
        Map<String, String> concurrentHashMap = createMap(true);
        addValue(concurrentHashMap);

        System.out.println("\nHashMap");
        Map<String, String> hashMap = createMap(false);
        addValue(hashMap);
    }

    /**
     * Сначала выводит содержимое объекта в консоль.
     * После этого с использованием итератора выполняется перебор всего набора данных объекта,
     * и для ключа со значением "2" формируется новый объект, который добавляется в набор.
     * Во время перебора значения набора выводятся в консоль.
     * В заключение повторно выводится содержимое объекта в консоль.
     */
    private static void addValue(Map<String, String> map) {
        System.out.println("  before iterator : " + map);

        Iterator<String> it = map.keySet().iterator();
        System.out.print("  cycle : ");
        while(it.hasNext()){
            String key = it.next(); // Место ошибки, т.к. итератор вызывает исключение при обращении к следующему объекту, если набор изменился.
            if (key.equals("2")) {
                map.put(key + "new", "222");
            } else
                System.out.print("  " + key + "=" + map.get(key));
        }
        System.out.println("\n  after iterator : " + map);
    }

    /**
     * Метод createMap создает объект типа Map<String, String> и наполняет объект некоторыми значениями.
     * @param concurrent использовать ConcurrentHashMap или HashMap.
     */
    private static Map<String, String> createMap(boolean concurrent) {
        Map<String, String> map;
        if (concurrent)
            map = new ConcurrentHashMap<>();
        else
            map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "1");
        map.put("3", "1");
        map.put("4", "1");
        map.put("5", "1");
        map.put("6", "1");
        return map;
    }

}

/**
 * вывод в консоль:
 *
 * ConcurrentHashMap
 *   before iterator : {1=1, 2=1, 3=1, 4=1, 5=1, 6=1}
 *   cycle :   1=1  3=1  4=1  5=1  6=1  2new=222
 *   after iterator : {1=1, 2=1, 3=1, 4=1, 5=1, 6=1, 2new=222}
 *
 * HashMap
 *   before iterator : {1=1, 2=1, 3=1, 4=1, 5=1, 6=1}
 *   cycle :   1=1Exception in thread "main" java.util.ConcurrentModificationException
 * 	at java.base/java.util.HashMap$HashIterator.nextNode(HashMap.java:1493)
 * 	at java.base/java.util.HashMap$KeyIterator.next(HashMap.java:1516)
 * 	at ru.chirkov.cheat.sheet.multithreading.collections.ConcurrentHashMapExample.addValue(ConcurrentHashMapExample.java:38)
 * 	at ru.chirkov.cheat.sheet.multithreading.collections.ConcurrentHashMapExample.main(ConcurrentHashMapExample.java:22)
 */
