package com.fengz.softjoke.business1.presenter;

import com.fengz.softjoke.base.Constants;
import com.fengz.softjoke.base.mvp.BasePresenter;
import com.fengz.softjoke.business1.contract.LoginContract;
import com.fengz.softjoke.business1.model.PreferencesRepository;
import com.fengz.softjoke.business1.model.api.B1Repository;
import com.fengz.softjoke.business1.model.entity.UserModel;
import com.fengz.softjoke.di.ActivityScope;
import com.fengz.softjoke.http.BaseObserver.BaseObserver;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

//@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    B1Repository mB1Repository;
    PreferencesRepository mPreRepository;

    @Inject
    public LoginPresenter(B1Repository mB1Repository, PreferencesRepository preRepository) {
        this.mB1Repository = mB1Repository;
        this.mPreRepository = preRepository;
    }

    @Override
    public void login(String userName, String password) {
        userLogin(userName, password);
    }

    @Override
    public String[] getLastUserInfo() {
        return mPreRepository.getLoginInfo();
    }

    private void userLogin(String userName, String password) {
        mB1Repository.loginUser(userName, password)
                .compose(getView().getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<UserModel>() {
                    @Override
                    public void onErrors(String e) {
                        getView().clearPwd();
                    }

                    @Override
                    public void onResponse(UserModel response) {
                        saveUserInfo(userName, password, response);
                        getView().loginSuccess();
                    }

                    @Override
                    public void onAfter() {
                        getView().dismissLoading();
                    }

                    @Override
                    public void onBefore(Disposable disposable) {
                        getView().showLoading();
                    }
                });
    }

    private void saveUserInfo(String userName, String password, UserModel userModel) {
        mPreRepository.setLoginInfo(userName, password);
        Constants.setUser(userModel);
    }

}
