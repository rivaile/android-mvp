package com.oldnum7.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v13.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.widget.ImageView;

import com.oldnum7.R;
import com.oldnum7.base.BaseAppActivity;
import com.oldnum7.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseAppActivity implements ViewPropertyAnimatorListener {

    @BindView(R.id.iv_splash)
    ImageView mIvSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setUnBinder(ButterKnife.bind(this));

        ViewCompat.animate(mIvSplash).scaleX(1.0f).scaleY(1.0f).setListener(this).setDuration(2000);
    }

    @Override
    public void onAnimationStart(View view) {

    }

    @Override
    public void onAnimationEnd(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onAnimationCancel(View view) {

    }
}
