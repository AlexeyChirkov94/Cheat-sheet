package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.hardcode.around;

public class WorkerBean {

    public void doSomeWork(int noOfTimes){
        for (int х = 0; х < noOfTimes; х++) {
            work();
        }
    }

    private void work(){
        System.out.print("");
    }
}

