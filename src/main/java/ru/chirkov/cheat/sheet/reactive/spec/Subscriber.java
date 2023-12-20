package ru.chirkov.cheat.sheet.reactive.spec;

public interface Subscriber<T> {

    void onSubscribe(Subscription subscription);
    void onNext(T value);
    void onComplete();
}
