package com.fengz.softjoke.business1.presenter;

import com.fengz.softjoke.base.mvp.BasePresenter;
import com.fengz.softjoke.business1.contract.HomeContract;

import javax.inject.Inject;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Inject
    public HomePresenter() {
    }

    @Override
    public void getData() {
    }

    @Override
    public void loadmore(int page) {

    }

    @Override
    public void refreshData() {
        getView().refreshFinish();
    }
}
