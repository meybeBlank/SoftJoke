package com.fengz.softjoke.business1.contract;

import com.fengz.softjoke.base.mvp.IPresenter;
import com.fengz.softjoke.base.mvp.IView;

public class MineContract {
    public interface View extends IView {
        void logout();
    }

    public interface Presenter extends IPresenter<View> {
        void logout();
    }
}
