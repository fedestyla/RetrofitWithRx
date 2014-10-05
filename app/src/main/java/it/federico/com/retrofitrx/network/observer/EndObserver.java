package it.federico.com.retrofitrx.network.observer;

import rx.Observer;

/**
 * An {@link rx.Observer} that always informs when it's ended.
 */
public abstract class EndObserver<T> implements Observer<T> {


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable throwable) {
    }


    @Override
    public void onNext(final T t) {

    }
}
