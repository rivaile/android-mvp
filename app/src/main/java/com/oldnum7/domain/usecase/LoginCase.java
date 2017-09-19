package com.oldnum7.domain.usecase;

import com.oldnum7.data.TasksRepository;
import com.oldnum7.data.entity.LoginEntity;
import com.oldnum7.mvp.base.BaseApplication;
import com.oldnum7.utils.Des3;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/09/06/13:51
 *       desc   : 登录的用例...
 *       version: 1.0
 * </pre>
 */
public class LoginCase extends UseCase<LoginEntity, LoginCase.Params> {

    private final TasksRepository mRepository;

    @Inject
    public LoginCase() {
        mRepository = BaseApplication.getAppComponent().getRepository();
    }

    @Override
    Observable<LoginEntity> buildUseCaseObservable(Params params) {
        String encodePwd = "";
        try {
            encodePwd = Des3.encode(params.pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mRepository.login(params.userName, encodePwd);
    }

    public static final  class Params {

        private final String userName;
        private final String pwd;

        public Params(String userName, String pwd) {
            this.userName = userName;
            this.pwd = pwd;
        }

        public static Params params(String userName, String pwd) {
            return new Params(userName, pwd);
        }
    }

}
