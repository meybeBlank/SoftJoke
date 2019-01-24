package com.fengz.softjoke.business1.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

/**
 * 创建时间：2019/1/22
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：搞笑段子 adapter
 */
public class FunnyStoryAdapter extends RecyclerView.Adapter<FunnyStoryAdapter.ViewHolder> {

    private List<JokeModule> mData;

    public FunnyStoryAdapter(List<JokeModule> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_funny_story, viewGroup, false);
        return new ViewHolder(view, viewGroup.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setContent(mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_content_story_item)
        TextView mTvContent;
        @BindView(R.id.img_portrait)
        ImageView mImgPortrait;
        @BindView(R.id.tv_author)
        TextView mTvAuthor;
        @BindView(R.id.tv_date)
        TextView mTvDate;
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
            mTvContent.setText(module.getText());
            ImageUtils.loadCircleImage(context, module.getProfile_image(), mImgPortrait);
            mTvAuthor.setText(module.getName());
            mTvDate.setText(module.getCreated_at());
            mCbLike.setText(module.getLove());
            mCbDislike.setText(module.getHate());
            mCbComment.setText(module.getComment());
        }
    }
}
