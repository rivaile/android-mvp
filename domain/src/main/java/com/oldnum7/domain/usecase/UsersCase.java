package com.oldnum7.domain.usecase;

import android.util.Log;

import com.oldnum7.data.DataRepository;
import com.oldnum7.data.entity.T;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/02/17:08
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public class UsersCase extends UseCase<List<T>, UsersCase.Params> {

    @Inject
    DataRepository mTasksRepository;

    @Inject
    public UsersCase(DataRepository tasksRepository) {
        this.mTasksRepository = tasksRepository;
    }

    @Override
    Observable<List<T>> buildUseCaseObservable(Params params) {

        Observable<List<T>> tasks = mTasksRepository
                .getUsers();
        Observable<T> userObservable = tasks
                .flatMap(new Function<List<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(List<T> userEntities) throws Exception {
                        return Observable.fromIterable(userEntities);
                    }
                });

        return userObservable
                .toList()
                .flatMapObservable(new Function<List<T>, ObservableSource<List<T>>>() {
                    @Override
                    public ObservableSource<List<T>> apply(@NonNull List<T> userEntities) throws Exception {
                        return Observable.just(userEntities);
                    }
                }).doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e("TAG", "doOnTerminate: ");
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e("TAG", "doFinally: ");
                    }
                });
    }

    public void refreshTasks() {
        mTasksRepository.refreshTasks();
    }

    public static final class Params {

        private final int userId;

        private Params(int userId) {
            this.userId = userId;
        }

        public static Params forUser(int userId) {
            return new Params(userId);
        }
    }

}
