package com.fengz.softjoke.business1.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fengz.softjoke.R;
import com.fengz.softjoke.base.mvp.APresenter;
import com.fengz.softjoke.base.mvp.BaseActivity;
import com.fengz.softjoke.business1.contract.WelcomeContract;
import com.fengz.softjoke.business1.presenter.WelcomePresenter;
import com.fengz.softjoke.business1.ui.Navigator;

import javax.inject.Inject;

public class WelcomeActivity extends BaseActivity implements WelcomeContract.View {

    @Inject
    @APresenter
    WelcomeContract.Presenter mWelPresenter;
    @Inject
    Navigator mNavigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        String[] userInfo = mWelPresenter.getLastUserInfo();
        if (userInfo == null || userInfo.length < 2 || userInfo[0] == null) {
            launchLoginAct();
        } else {
            mWelPresenter.login(userInfo[0], userInfo[1]);
        }
    }

    @Override
    public void launchLoginAct() {
        mNavigator.navigator2LoginAct(this);
        finish();
    }

    @Override
    public void launchMainAct() {
        mNavigator.navigator2MainAct(this);
        finish();
    }
}
