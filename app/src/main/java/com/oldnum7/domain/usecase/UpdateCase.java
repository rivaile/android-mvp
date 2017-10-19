package com.oldnum7.domain.usecase;

import android.util.Log;

import com.oldnum7.data.DataRepository;
import com.oldnum7.data.entity.VersionEntity;

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
public class UpdateCase extends UseCase<VersionEntity, UpdateCase.Params> {

    private DataRepository mRepository;

    @Inject
    public UpdateCase(DataRepository repository) {
        this.mRepository = repository;
    }

    @Override
    Observable<VersionEntity> buildUseCaseObservable(Params params) {
        Log.e("TAG", "buildUseCaseObservable: "+ mRepository);
        return mRepository.updateVersion(params.clientVersion);
    }

    public static final class Params {

        private final String clientVersion;

        public Params(String clientVersion) {
            this.clientVersion = clientVersion;
        }

        public static Params params(String clientVersion) {
            return new Params(clientVersion);
        }
    }

}
