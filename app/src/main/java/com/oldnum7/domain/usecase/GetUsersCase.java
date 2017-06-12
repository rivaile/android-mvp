package com.oldnum7.domain.usecase;

import android.util.Log;

import com.oldnum7.base.App;
import com.oldnum7.data.TasksRepository;
import com.oldnum7.data.entity.UserEntity;
import com.oldnum7.data.local.TasksLocalDataSource;
import com.oldnum7.data.remote.TasksRemoteDataSource;

import java.util.List;

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
public class GetUsersCase extends UseCase<List<UserEntity>, GetUsersCase.Params> {

    private final TasksRepository mTasksRepository = TasksRepository.getInstance(new TasksRemoteDataSource(), new TasksLocalDataSource(App.getmContext()));

    public GetUsersCase() {
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
