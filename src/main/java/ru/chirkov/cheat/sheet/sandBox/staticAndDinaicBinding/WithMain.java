package ru.chirkov.cheat.sheet.sandBox.staticAndDinaicBinding;


public class WithMain {

    public static void main(String[] args){
        Runner runner = new Runner();
        One one = new One();
        Two two = new Two();
        runner.run(two);
    }
}
