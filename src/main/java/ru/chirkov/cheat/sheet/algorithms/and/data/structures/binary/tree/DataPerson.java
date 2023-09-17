package ru.chirkov.cheat.sheet.algorithms.and.data.structures.binary.tree;

import lombok.Getter;
import lombok.Setter;

public class DataPerson {

    public DataPerson(int id) {
        this.id = id;
    }

//    public DataPerson(int id, String name, int age) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//    }

    @Getter
    private final int id;

//    @Getter @Setter
//    private String name;
//
//    @Getter @Setter
//    private int age;

    @Override
    public String toString() {
        return "DataPerson{" +
                "id=" + id +
//                ", name='" + name + '\'' +
//                ", age=" + age +
                '}';
    }
}
