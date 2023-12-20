package ru.chirkov.cheat.sheet.reactive.spec.pub;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.chirkov.cheat.sheet.reactive.spec.Publisher;
import ru.chirkov.cheat.sheet.reactive.spec.Subscriber;
import ru.chirkov.cheat.sheet.reactive.spec.Subscription;

@AllArgsConstructor
public class JustPublisher<T> implements Publisher<T> {

    private final T[] values;

    @Override
    public void subscribe(Subscriber<? super T> subscriber) {
        Subscription subscription = new JustSubscription(subscriber);
        subscriber.onSubscribe(subscription);
    }

    @RequiredArgsConstructor
    private class JustSubscription implements Subscription {

        private final Subscriber<? super T> subscriber;
        int position = 0;

        @Override
        public void request(int n) {
            for (int i = 0; i < n; i++){
                if (position == values.length){
                    subscriber.onComplete();
                    return;
                }
                subscriber.onNext(values[position++]);
            }
        }

    }
}
