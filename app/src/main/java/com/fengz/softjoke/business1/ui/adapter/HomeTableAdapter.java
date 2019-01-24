package com.fengz.softjoke.business1.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fengz.softjoke.R;
import com.fengz.softjoke.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间：2019/1/24
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：首页Table Adapter
 */
public class HomeTableAdapter extends RecyclerView.Adapter<HomeTableAdapter.ViewHolder> {

    private String[] mData;
    private String[] mTitles = {"标题", "测试", "内容", "词穷", "无语", "标题"};

    public HomeTableAdapter(String[] data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_table_info, viewGroup, false);
        return new ViewHolder(viewGroup.getContext(), view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (mData != null && mData.length > 0) {
            viewHolder.setData(mData[i % 5], mTitles[i % 5]);
        }
    }

    @Override
    public int getItemCount() {
        return mData.length * 2;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_table_item)
        ImageView mImg;
        @BindView(R.id.tv_table_item)
        TextView mTv;

        private Context context;

        public ViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        void setData(String img, String title) {
            ImageUtils.loadCircleImage(context, img, mImg);
            mTv.setText(title);
        }
    }
}
