package ru.chirkov.cheat.sheet.multithreading.ConcurrencyInPracticeExamples;

import net.jcip.annotations.NotThreadSafe;

/**
 * ThisEscape
 * <p/>
 * Implicitly allowing the this reference to escape
 * <p/>
 * Когда класс ThisEscape публикует слушателя EventListener, он неявно публикует и окаймляющий его экземпляр ThisEscape,
 * потому что экземпляры внутреннего класса содержат скрытую ссылку на него. Потокобезопасный аналог - {@link SafeListener}
 * <p/>
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
public class ThisEscape {

    public ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {
            public void onEvent(Event e) {
                doSomething(e);
            }
        });
    }

    void doSomething(Event e) {
    }


    interface EventSource {
        void registerListener(EventListener e);
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {
    }
}

