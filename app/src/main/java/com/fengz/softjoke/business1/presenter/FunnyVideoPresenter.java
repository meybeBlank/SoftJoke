package com.fengz.softjoke.business1.presenter;

import android.util.Log;

import com.fengz.softjoke.base.mvp.BasePresenter;
import com.fengz.softjoke.business1.contract.FunnyVideoContract;
import com.fengz.softjoke.business1.contract.MineContract;
import com.fengz.softjoke.business1.model.api.B1Repository;
import com.fengz.softjoke.business1.model.entity.JokeModule;
import com.fengz.softjoke.http.BaseObserver.BaseObserver;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class FunnyVideoPresenter extends BasePresenter<FunnyVideoContract.View> implements FunnyVideoContract.Presenter {

    public static final int JOKES_TYPE_VIDEO = 4;
    B1Repository mRepository;

    @Inject
    public FunnyVideoPresenter(B1Repository mRepository) {
        this.mRepository = mRepository;
    }


    @Override
    public void getData() {
        mRepository.getJokes(JOKES_TYPE_VIDEO, 1)
                .compose(getView().getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<List<JokeModule>>() {
                    @Override
                    public void onErrors(String e) {
                        getView().showErr(e);
                    }

                    @Override
                    public void onResponse(List<JokeModule> response) {
                        if (response == null || response.size() <= 0) {
                            getView().showEmpty();
                        } else {
                            getView().showContext();
                            getView().setData(response);
                        }
                    }

                    @Override
                    public void onBefore(Disposable disposable) {
                        getView().showLoading();
                    }
                });
    }

    @Override
    public void refreshData() {
        mRepository.getJokes(JOKES_TYPE_VIDEO, 1)
                .compose(getView().getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<List<JokeModule>>() {
                    @Override
                    public void onErrors(String e) {
                        getView().showErr(e);
                    }

                    @Override
                    public void onResponse(List<JokeModule> response) {
                        if (response == null || response.size() <= 0) {
                            getView().showEmpty();
                        } else {
                            getView().showContext();
                            getView().setData(response);
                        }
                    }

                    @Override
                    public void onAfter() {
                        getView().refreshFinish();
                    }
                });
    }

    @Override
    public void loadmore(int page) {
        mRepository.getJokes(JOKES_TYPE_VIDEO, page)
                .compose(getView().getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<List<JokeModule>>() {
                    @Override
                    public void onErrors(String e) {
                    }

                    @Override
                    public void onResponse(List<JokeModule> response) {
                        if (response == null || response.size() <= 0) {
                            getView().showNomore(true);
                        } else {
                            getView().showNomore(false);
                            getView().addData(response, page);
                        }
                    }

                    @Override
                    public void onAfter() {
                        getView().loadFinish();
                    }
                });
    }
}
