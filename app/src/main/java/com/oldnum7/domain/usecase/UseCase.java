package com.oldnum7.domain.usecase;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/02/16:03
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public abstract class UseCase<T, Params> {

    private final CompositeDisposable disposables;


    public UseCase() {
        this.disposables = new CompositeDisposable();
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    abstract Observable<T> buildUseCaseObservable(Params params);

    public void execute(DisposableObserver<T> observer, Params params) {
        if (observer == null) {
            throw new NullPointerException("observer cannot be null");
        }
        Observable<T> observable = buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        addDisposable(observable.subscribeWith(observer));
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    private void addDisposable(Disposable disposable) {
        if (disposable == null || disposables == null) {
            throw new NullPointerException("disposable cannot be null");
        }
        disposables.add(disposable);
    }

}
