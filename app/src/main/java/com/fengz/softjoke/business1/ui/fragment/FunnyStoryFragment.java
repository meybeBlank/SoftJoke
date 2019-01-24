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
import com.fengz.softjoke.business1.contract.FunnyStoryContract;
import com.fengz.softjoke.business1.model.entity.JokeModule;
import com.fengz.softjoke.business1.ui.adapter.FunnyStoryAdapter;
import com.fengz.softjoke.common.MultipleRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import yuan.kuo.yu.view.YRecyclerView;

/**
 * 创建时间：2019/1/22
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：搞笑段子 Fragment
 */
public class FunnyStoryFragment extends BaseFragment implements FunnyStoryContract.View {

    @Inject
    @APresenter
    FunnyStoryContract.Presenter mPresenter;

    @BindView(R.id.recycler_funny_story_frg)
    YRecyclerView mRecycler;
    @BindView(R.id.multiple_funny_story_frg)
    MultipleRelativeLayout mMultiple;

    private List<JokeModule> mData = new ArrayList<>();
    private FunnyStoryAdapter mAdapter;
    private int mPage = 1;

    public static FunnyStoryFragment newInstance() {
        Bundle args = new Bundle();
        FunnyStoryFragment fragment = new FunnyStoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_funny_story, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI();
        mPresenter.getData();
    }

    private void initUI() {
        mMultiple.setOnRetryClickListener(view -> mPresenter.getData());
        mAdapter = new FunnyStoryAdapter(mData);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecycler.setRefreshEnabled(true);
        mRecycler.setLoadMoreEnabled(true);
        mRecycler.setRefreshAndLoadMoreListener(new YRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshData();
            }

            @Override
            public void onLoadMore() {
                mPresenter.loadmore(mPage++);
            }
        });
    }

    @Override
    public void showErr(String msg) {
        mMultiple.showError();
    }

    @Override
    public void setData(List<JokeModule> data) {
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmpty() {
        mMultiple.showEmpty();
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
    public void addData(List<JokeModule> data, int page) {
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
        mPage = page;
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
