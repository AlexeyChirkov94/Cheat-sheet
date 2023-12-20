package ru.chirkov.cheat.sheet.reactive.spec;

import ru.chirkov.cheat.sheet.reactive.spec.pub.JustPublisher;
import ru.chirkov.cheat.sheet.reactive.spec.pub.MapPublisher;
import ru.chirkov.cheat.sheet.reactive.spec.sub.CollectingSubscriber;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Publisher<T> {

    @SafeVarargs
    static <V> Publisher<V> just(V... values){
        return new JustPublisher<>(values);
    }

    void subscribe(Subscriber<? super T> subscriber);

    default <R> Publisher<R> map(Function<T, R> mapper){
        return new MapPublisher<>(this, mapper);
    }

    default Publisher<T> peek(Consumer<T> consumer){
        return new MapPublisher<>(this, value-> {
            consumer.accept(value);
            return value;
        });
    }

    default List<T> collect(){
        CollectingSubscriber<T> subscriber = new CollectingSubscriber<>();
        subscribe(subscriber);
        return subscriber.blockingGet();
    }

}
