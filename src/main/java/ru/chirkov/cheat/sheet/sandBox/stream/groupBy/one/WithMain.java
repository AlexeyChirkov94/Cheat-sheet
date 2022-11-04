package ru.chirkov.cheat.sheet.sandBox.stream.groupBy.one;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WithMain {

    public static void main(String[] args) {

        Student st1 = new Student("st1", 1, 1);
        Student st2 = new Student("st2", 2, 2);
        Student st3 = new Student("st3", 3, 1);
        Student st4 = new Student("st4", 4, 2);
        Student st5 = new Student("st5", 5, 1);
        Student st6 = new Student("st6", 6, 2);
        Student st7 = new Student("st7", 7, 2);

        List<Student> students = Arrays.asList(st1, st2, st3, st4, st5, st6, st7);

        Map<Integer, List<Student>> studentsByGroup = students.stream().collect(Collectors.groupingBy(Student::getGroupId));
        System.out.println(studentsByGroup);

        Map<Integer, Long> countsInGroup = students.stream().collect(Collectors.groupingBy(Student::getGroupId, Collectors.counting()));
        System.out.println(countsInGroup);
    }

}
