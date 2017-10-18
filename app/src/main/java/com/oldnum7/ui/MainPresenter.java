package com.oldnum7.ui;

/**
 * author : denglin
 * time   : 2017/05/31/13:54
 * desc   :
 * version: 1.0
 */
public class MainPresenter extends IMainContract.Presenter {
    @Override
    void loadData(boolean forceUpdate) {

    }

    @Override
    void getUsers(int since, int per_page) {

    }

    @Override
    void getUsers() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

//    private final String TAG = getClass().getSimpleName();
//
//    private final UsersCase mUsersCase;
//    private StatusLayoutManager statusLayoutManager;
//
//    private final IMainContract.View mMainView;
//
//    private boolean mFirstLoad = true;
//
//    @Inject
//    MainPresenter(IMainContract.View view, UsersCase usersCase) {
//        this.mMainView = view;
//        this.mUsersCase = usersCase;
//    }
//
//    public void setStatusLayoutManager(StatusLayoutManager statusLayoutManager) {
//        this.statusLayoutManager = statusLayoutManager;
//    }
//
//    @Override
//    public void getUsers(int since, int per_page) {
//
//    }
//
//    @Override
//    public void getUsers() {
//
//    }
//
//    @Override
//    public void subscribe() {
//        loadData(false);
//    }
//
//    @Override
//    public void unsubscribe() {
//        mUsersCase.dispose();
//    }
//
//    public void loadData(boolean forceUpdate) {
//        // Simplification for sample: a network reload will be forced on first load.
//        loadData(forceUpdate || mFirstLoad, true);
//        mFirstLoad = false;
//    }
//
//
//    /**
//     * @param forceUpdate   Pass in true to refresh the data in the {@link }
//     * @param showLoadingUI Pass in true to display a loading icon in the UI
//     */
//    private void loadData(final boolean forceUpdate, final boolean showLoadingUI) {
//        if (showLoadingUI) {
//            mMainView.setLoadingIndicator(true);
//        }
//        if (forceUpdate) {
//            mUsersCase.refreshTasks();
//        }
//
//        mUsersCase.execute(new HttpObserver<List<T>>(statusLayoutManager) {
//            @Override
//            public void onNext(List<T> userEntities) {
//                mMainView.getUsers(userEntities);
//            }
//
//        }, null);
//
//    }
//
//    private void processTasks(@NonNull List<T> tasks) {
//        if (tasks.isEmpty()) {
//            // Show a message indicating there are no tasks for that filter type.
////            processEmptyTasks();
//        } else {
//            // Show the list of tasks
////            mTasksView.showTasks(tasks);
//            // Set the filter label's text.
////            showFilterLabel();
//        }
//    }

}
