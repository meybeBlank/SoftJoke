package com.fengz.softjoke.business1.contract;

import com.fengz.softjoke.base.mvp.IPresenter;
import com.fengz.softjoke.base.mvp.IView;

public class HomeContract {
    public interface View extends IView {
        void showErr(String msg);
//        void setData(List<JokeModule> data);
        void showLoading();
        void showContext();

        void refreshFinish();

//        void addData(List<JokeModule> data,int page);
        void showNomore(boolean nomore);
        void loadFinish();
    }

    public interface Presenter extends IPresenter<View> {
        void getData();

        void loadmore(int page);

        void refreshData();
    }
}
