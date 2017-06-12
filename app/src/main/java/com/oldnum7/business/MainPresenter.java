package com.oldnum7.business;

import android.support.annotation.NonNull;

import com.oldnum7.base.App;
import com.oldnum7.base.HttpObserver;
import com.oldnum7.data.TasksRepository;
import com.oldnum7.data.entity.UserEntity;
import com.oldnum7.data.local.TasksLocalDataSource;
import com.oldnum7.data.remote.TasksRemoteDataSource;
import com.oldnum7.domain.usecase.GetUsersCase;
import com.oldnum7.mvp.BaseMvpPresenter;
import com.oldnum7.status.StatusLayoutManager;

import java.util.List;

/**
 * author : denglin
 * time   : 2017/05/31/13:54
 * desc   :
 * version: 1.0
 */
public class MainPresenter extends BaseMvpPresenter<IMainContract.View> implements IMainContract.Presenter {

    private final String TAG = getClass().getSimpleName();

    @NonNull
    private final TasksRepository mTasksRepository = TasksRepository.getInstance(new TasksRemoteDataSource(), new TasksLocalDataSource(App.getmContext()));

    private final GetUsersCase getUserListUseCase;
    private StatusLayoutManager statusLayoutManager;

    private boolean mFirstLoad = true;

    public MainPresenter() {
        getUserListUseCase = new GetUsersCase();
    }

    public void setStatusLayoutManager(StatusLayoutManager statusLayoutManager) {
        this.statusLayoutManager = statusLayoutManager;
    }

    @Override
    public void getUsers(int since, int per_page) {

    }

    @Override
    public void getUsers() {

    }

    @Override
    public void subscribe() {
        loadData(false);
    }

    @Override
    public void unsubscribe() {
        getUserListUseCase.dispose();
    }

    public void loadData(boolean forceUpdate) {
        // Simplification for sample: a network reload will be forced on first load.
        loadData(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }


    /**
     * @param forceUpdate   Pass in true to refresh the data in the {@link }
     * @param showLoadingUI Pass in true to display a loading icon in the UI
     */
    private void loadData(final boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            getView().setLoadingIndicator(true);
        }
        if (forceUpdate) {
            mTasksRepository.refreshTasks();
        }


        getUserListUseCase.execute(new HttpObserver<List<UserEntity>>(statusLayoutManager) {
            @Override
            public void onNext(List<UserEntity> userEntities) {
                getView().getUsers(userEntities);
            }

//            @Override
//            public void onComplete() {
//                super.onComplete();
//                getView().setLoadingIndicator(false);
//            }
        }, null);

    }

    private void processTasks(@NonNull List<UserEntity> tasks) {
        if (tasks.isEmpty()) {
            // Show a message indicating there are no tasks for that filter type.
//            processEmptyTasks();
        } else {
            // Show the list of tasks
//            mTasksView.showTasks(tasks);
            // Set the filter label's text.
//            showFilterLabel();
        }
    }

}
