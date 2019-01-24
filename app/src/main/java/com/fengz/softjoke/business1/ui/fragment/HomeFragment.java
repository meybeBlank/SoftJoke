package com.fengz.softjoke.business1.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengz.softjoke.R;
import com.fengz.softjoke.base.mvp.APresenter;
import com.fengz.softjoke.base.mvp.BaseFragment;
import com.fengz.softjoke.business1.contract.HomeContract;
import com.fengz.softjoke.business1.ui.adapter.HomeAdapter;
import com.fengz.softjoke.common.MultipleRelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import yuan.kuo.yu.view.YRecyclerView;

/**
 * 创建时间：2019/1/22
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：首页Fragment
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {

    @Inject
    @APresenter
    HomeContract.Presenter mPresenter;

    @BindView(R.id.multiple_home_frg)
    MultipleRelativeLayout mMultiple;
    @BindView(R.id.recycler_home_frg)
    YRecyclerView mRecycler;

    private HomeAdapter mHomeAdapter;
    private List<Object> mHomeData = new ArrayList<>();

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI();
        mPresenter.getData();
    }

    private void initUI() {
        mMultiple.setOnRetryClickListener(view -> mPresenter.getData());
        for (int i = 0; i < 20; i++) {
            mHomeData.add("1");
        }
        mHomeAdapter = new HomeAdapter(mHomeData, getTimeTask());
        mRecycler.setAdapter(mHomeAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecycler.setRefreshEnabled(true);
        mRecycler.setLoadMoreEnabled(false);
        mRecycler.setRefreshAndLoadMoreListener(new YRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshData();
            }

            @Override
            public void onLoadMore() {
                mPresenter.loadmore(1);
            }
        });
    }

    private Observable getTimeTask() {
        return Observable.interval(2, 5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .compose(getLifecycleProvider().bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void showErr(String msg) {
        mMultiple.showError();
    }

    @Override
    public void showLoading() {
        mMultiple.showLoading();
    }

    @Override
    public void showContext() {
        mMultiple.showContent();
    }

    @Override
    public void refreshFinish() {
        mRecycler.setReFreshComplete();
    }

    @Override
    public void showNomore(boolean nomore) {
        mRecycler.setNoMoreData(nomore);
    }

    @Override
    public void loadFinish() {
        mRecycler.setloadMoreComplete();
    }
}
