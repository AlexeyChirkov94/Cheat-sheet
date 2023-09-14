package ru.chirkov.cheat.sheet.algorithms.and.data.structures.massive;

public class SandBox {

    public static void main(String[] args) {
        Data[] dates = new Data[5];
        Data data1 = new Data(1, "one");
        dates[1] = data1;
        Data data0 = dates[0];
        System.out.println(data0);
        System.out.println(data1);
    }
}

class Data {

    public Data(int id, String name) {
        this.id = id;
        this.name = name;
    }

    int id;
    String name;

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
