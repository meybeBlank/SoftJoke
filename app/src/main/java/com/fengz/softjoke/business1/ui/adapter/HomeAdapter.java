package com.fengz.softjoke.business1.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengz.softjoke.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 创建时间：2019/1/24
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：首页Adapter
 */
public class HomeAdapter<T> extends RecyclerView.Adapter {

    private static final int ITEM_TYPE_GALLERY = 0;
    private static final int ITEM_TYPE_TABLE = 1;
    private static final int ITEM_TYPE_LIST = 2;

    private static final String[] GalleryImgs = {"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=4215349948,301044860&fm=11&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=281369701,3608210878&fm=11&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3944482284,2123354859&fm=11&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2177371868,1295341607&fm=11&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3809944434,2222886474&fm=11&gp=0.jpg",};
    private static final int HOME_TABLE_SIZE = 5;

    private List<T> mData;
    private Observable mTimeTask;

    public HomeAdapter(List<T> data, Observable timeTask) {
        mData = data;
        mTimeTask = timeTask;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return ITEM_TYPE_GALLERY;
            case 1:
                return ITEM_TYPE_TABLE;
            default:
                return ITEM_TYPE_LIST;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        switch (getItemViewType(i)) {
            case ITEM_TYPE_GALLERY: {
                View view = LayoutInflater.from(context).inflate(R.layout.item_home_gallery, viewGroup, false);
                return new GalleryHolder(context, view);
            }
            case ITEM_TYPE_TABLE: {
                View view = LayoutInflater.from(context).inflate(R.layout.item_home_table, viewGroup, false);
                return new TableHolder(context, view);
            }
            case ITEM_TYPE_LIST: {
                View view = LayoutInflater.from(context).inflate(R.layout.item_home_list, viewGroup, false);
                return new ListHolder(context, view);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    /**
     * 创建时间：2019/1/24
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：首页Banner Holder
     */
    class GalleryHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recycler_gallery_home_item)
        RecyclerView mRecycler;

        private Context context;
        private LinearLayoutManager mLayoutManager;

        public GalleryHolder(Context context, @NonNull View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);

            initRecycler();
        }

        private void initRecycler() {
            mLayoutManager = new LinearLayoutManager(context);
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecycler.setLayoutManager(mLayoutManager);
            mRecycler.setAdapter(new HomeGalleryAdapter(GalleryImgs));
            PagerSnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(mRecycler);
            int i = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2) % GalleryImgs.length - 1;
            mRecycler.scrollToPosition(i);
            mRecycler.smoothScrollToPosition(i + 1);
            startInteral();
        }

        private void startInteral() {
            Disposable disposable = mTimeTask.subscribe(aLong -> {
                int firstItem = mLayoutManager.findFirstVisibleItemPosition();
                mRecycler.smoothScrollToPosition(firstItem + 2);
            });
        }
    }

    /**
     * 创建时间：2019/1/24
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：首页表格快捷导航入口
     */
    class TableHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recycler_table_home_item)
        RecyclerView mRecycler;

        private Context context;

        public TableHolder(Context context, @NonNull View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);

            initRecycler();
        }

        private void initRecycler() {
            mRecycler.setLayoutManager(new GridLayoutManager(context, HOME_TABLE_SIZE));
            mRecycler.setAdapter(new HomeTableAdapter(GalleryImgs));
        }
    }

    /**
     * 创建时间：2019/1/24
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：首页的功能列表
     */
    class ListHolder extends RecyclerView.ViewHolder {

        private Context context;

        public ListHolder(Context context, @NonNull View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        private void setData() {

        }
    }
}
