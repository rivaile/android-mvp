/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.oldnum7.ui.dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.oldnum7.di.component.ActivityComponent;

/**
 * Created by janisharali on 21/03/17.
 */

public class RateUsDialog extends BaseDialog implements RatingDialogMvpView {

    private static final String TAG = "RateUsDialog";

//    @Inject
//    RatingDialogMvpPresenter<RatingDialogMvpView> mPresenter;


    public static RateUsDialog newInstance() {
        RateUsDialog fragment = new RateUsDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        View view = inflater.inflate(R.layout.dialog_rate_us, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {

//            component.inject(this);

//            setUnBinder(ButterKnife.bind(this, view));

//            mPresenter.onAttach(this);
        }

//        return view;
        return null;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    @Override
    protected void setUp(View view) {

//        mSubmitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPresenter.onRatingSubmitted(mRatingBar.getRating(), mMessage.getText().toString());
//            }
//        });

    }


//    @OnClick(R.id.btn_rate_on_play_store)
//    void onPlayStoreRateClick() {
//        mPresenter.onPlayStoreRatingClicked();
//    }


    @Override
    public void hideSubmitButton() {
//        mSubmitButton.setVisibility(View.GONE);
    }

    @Override
    public void dismissDialog() {
        super.dismissDialog(TAG);
    }

    @Override
    public void onDestroyView() {
//        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showSnackBar(String msg) {

    }
}