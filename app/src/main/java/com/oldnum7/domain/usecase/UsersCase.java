package com.oldnum7.domain.usecase;

import android.util.Log;

import com.oldnum7.data.TasksRepository;
import com.oldnum7.data.entity.UserEntity;

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
public class UsersCase extends UseCase<List<UserEntity>, UsersCase.Params> {

    @Inject
    TasksRepository mTasksRepository;

    @Inject
    public UsersCase(TasksRepository tasksRepository) {
        this.mTasksRepository = tasksRepository;
    }

    @Override
    Observable<List<UserEntity>> buildUseCaseObservable(Params params) {

        Observable<List<UserEntity>> tasks = mTasksRepository
                .getUsers();
        Observable<UserEntity> userObservable = tasks
                .flatMap(new Function<List<UserEntity>, ObservableSource<UserEntity>>() {
                    @Override
                    public ObservableSource<UserEntity> apply(List<UserEntity> userEntities) throws Exception {
                        return Observable.fromIterable(userEntities);
                    }
                });

        return userObservable
                .filter(userEntity -> true)
                .toList()
                .flatMapObservable(new Function<List<UserEntity>, ObservableSource<List<UserEntity>>>() {
                    @Override
                    public ObservableSource<List<UserEntity>> apply(@NonNull List<UserEntity> userEntities) throws Exception {
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
