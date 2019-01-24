package com.fengz.softjoke.business1.contract;

import com.fengz.softjoke.base.mvp.IPresenter;
import com.fengz.softjoke.base.mvp.IView;

public class MainContract {

    public interface View extends IView {
        void switchFragment(int position);
    }

    public interface Presenter extends IPresenter<View> {
    }
}
