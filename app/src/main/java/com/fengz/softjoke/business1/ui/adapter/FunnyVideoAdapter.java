package com.fengz.softjoke.business1.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.fengz.softjoke.R;
import com.fengz.softjoke.business1.model.entity.JokeModule;
import com.fengz.softjoke.utils.ImageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * 创建时间：2019/1/22
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：视频adapter
 */
public class FunnyVideoAdapter extends RecyclerView.Adapter {

    private List<JokeModule> mData;
    private static final int ITEM_TYPE_LANDSCAPE = 0;
    private static final int ITEM_TYPE_PORTRAIT = 1;

    public FunnyVideoAdapter(List<JokeModule> mData) {
        this.mData = mData;
    }

    @Override
    public int getItemViewType(int position) {
        JokeModule jokeModule = mData.get(position);
        if (equals(jokeModule.getWidth(), jokeModule.getHeight()) > 0) {
            return ITEM_TYPE_LANDSCAPE;
        } else {
            return ITEM_TYPE_PORTRAIT;
        }
    }

    /**
     * @return a>b返回>0  a<b返回<0
     */
    private int equals(String a, String b) {
        if (TextUtils.isEmpty(a) || TextUtils.isEmpty(b)) {
            throw new IllegalArgumentException("参数错误，数据异常");
        }
        return Integer.valueOf(a) - Integer.valueOf(b);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        switch (getItemViewType(i)) {
            case ITEM_TYPE_LANDSCAPE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_funny_video_landscape, viewGroup, false);
                break;
            case ITEM_TYPE_PORTRAIT:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_funny_video_portrait, viewGroup, false);
                break;
        }
        if (view == null) throw new IllegalArgumentException("View不能为空，类型异常");
        return new ViewHolder(view, viewGroup.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.setContent(mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_portrait)
        ImageView mImgPortrait;
        @BindView(R.id.tv_author)
        TextView mTvAuthor;
        @BindView(R.id.tv_date)
        TextView mTvDate;
        @BindView(R.id.tv_title_funny_story_item)
        TextView mTvTitle;
        @BindView(R.id.vv_funny_story_item)
        JzvdStd mVidoe;
        @BindView(R.id.cb_like)
        CheckBox mCbLike;
        @BindView(R.id.cb_dislike)
        CheckBox mCbDislike;
        @BindView(R.id.cb_comment)
        CheckBox mCbComment;

        private Context context;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
        }

        void setContent(JokeModule module) {
            ImageUtils.loadCircleImage(context, module.getProfile_image(), mImgPortrait);
            mTvAuthor.setText(module.getName());
            mTvDate.setText(module.getCreated_at());
            mTvTitle.setText(module.getText());
            mVidoe.setUp(module.getVideouri(), "", Jzvd.SCREEN_WINDOW_NORMAL);
            ImageUtils.loadImage(context, module.getImage1(), mVidoe.thumbImageView);
            mCbLike.setText(module.getLove());
            mCbDislike.setText(module.getHate());
            mCbComment.setText(module.getComment());
        }
    }
}
