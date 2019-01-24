package com.fengz.softjoke.business1.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fengz.softjoke.R;
import com.fengz.softjoke.base.Constants;
import com.fengz.softjoke.base.mvp.APresenter;
import com.fengz.softjoke.base.mvp.BaseFragment;
import com.fengz.softjoke.business1.contract.MineContract;
import com.fengz.softjoke.business1.ui.Navigator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建时间：2019/1/22
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：我的 Fragment
 */
public class MineFragment extends BaseFragment implements MineContract.View {

    @Inject
    @APresenter
    MineContract.Presenter mPresenter;
    @Inject
    Navigator mNavigator;

    @BindView(R.id.tv_user_mine_fra)
    TextView mTvUser;
    @BindView(R.id.btn_logout_mine_fra)
    Button mBtnLogout;

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        initUI();
    }

    private void initUI() {
        mBtnLogout.setVisibility(Constants.isLogin() ? View.VISIBLE : View.GONE);
        mTvUser.setText(Constants.isLogin() ? Constants.getUser().getPhone() : "点击登录");
    }

    @OnClick({R.id.btn_logout_mine_fra, R.id.tv_user_mine_fra})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_logout_mine_fra:
                mPresenter.logout();
                break;
            case R.id.tv_user_mine_fra:
                if (!Constants.isLogin()) {
                    mNavigator.navigator2LoginActBack(mPresenter.getContext());
                }
                break;
        }
    }

    @Override
    public void logout() {
        initUI();
        mNavigator.navigator2LoginActBack(mPresenter.getContext());
    }
}
