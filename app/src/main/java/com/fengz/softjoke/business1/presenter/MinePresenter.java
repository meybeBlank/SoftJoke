package com.fengz.softjoke.business1.presenter;

import com.fengz.softjoke.base.Constants;
import com.fengz.softjoke.base.mvp.BasePresenter;
import com.fengz.softjoke.business1.contract.HomeContract;
import com.fengz.softjoke.business1.contract.MineContract;
import com.fengz.softjoke.business1.model.PreferencesRepository;

import javax.inject.Inject;

public class MinePresenter extends BasePresenter<MineContract.View> implements MineContract.Presenter {

    PreferencesRepository mPreRepository;

    @Inject
    public MinePresenter(PreferencesRepository preRepository) {
        this.mPreRepository = preRepository;
    }

    @Override
    public void logout() {
        Constants.setUser(null);
        mPreRepository.setLoginInfo(null, null);
        getView().logout();
    }
}
