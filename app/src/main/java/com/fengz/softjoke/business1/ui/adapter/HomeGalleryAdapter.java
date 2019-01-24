package com.fengz.softjoke.business1.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fengz.softjoke.R;
import com.fengz.softjoke.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间：2019/1/24
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：首页Gallery Adapter
 */
public class HomeGalleryAdapter extends RecyclerView.Adapter<HomeGalleryAdapter.ViewHolder> {

    private String[] mData;

    public HomeGalleryAdapter(String[] data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_img_gallery, viewGroup, false);
        return new ViewHolder(viewGroup.getContext(), view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (mData != null && mData.length > 0) {
            viewHolder.setImg(mData[i % mData.length]);
        }
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_gallery_item)
        ImageView mImg;

        private Context context;

        public ViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        void setImg(String img) {
            ImageUtils.loadImage(context, img, mImg);
        }
    }
}
