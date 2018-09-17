package com.mouxuejie.ccvideo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.widget.Space;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mouxuejie.ccvideo.R;
import com.mouxuejie.ccvideo.ui.activity.VideoDetailActivity;
import com.mouxuejie.ccvideo.model.bean.FeedEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangxinghe on 12/9/2018.
 */

public class FeedAdapter extends RecyclerView.Adapter {
    private static final int NONE_VIEW_TYPE = 0;
    private static final int ITEM_VIEW_TYPE = 1;

    private Context mContext;
    private List<FeedEntity.IssueListEntity.ItemListEntity> mItemListEntities = new ArrayList<>();

    public FeedAdapter(Context context, List<FeedEntity.IssueListEntity.ItemListEntity> itemListEntities) {
        mContext = context;
        mItemListEntities = itemListEntities;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (ITEM_VIEW_TYPE == viewType) {
            return new FeedViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.feed_item_layout, parent, false));
        } else {
            return new NoneViewHolder(new Space(parent.getContext()));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof FeedViewHolder) {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)holder.itemView.getLayoutParams();
            params.height = 500 + (int)(Math.random() * 500);
            holder.itemView.setLayoutParams(params);

            FeedViewHolder feedViewHolder = (FeedViewHolder) holder;
            final String coverUrl = mItemListEntities.get(position).mData.mCover.mHomepage;
            final Uri coverUri = Uri.parse(coverUrl);
            feedViewHolder.mCoverIv.setImageURI(coverUri);
            feedViewHolder.mCoverIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    VideoDetailActivity.jump(mContext, mItemListEntities.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItemListEntities != null ? mItemListEntities.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if ("video".equals(mItemListEntities.get(position).mType)) {
            return ITEM_VIEW_TYPE;
        } else {
            return NONE_VIEW_TYPE;
        }
    }

    class FeedViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cover_feed)
        SimpleDraweeView mCoverIv;

        public FeedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class NoneViewHolder extends RecyclerView.ViewHolder {

        public NoneViewHolder(View itemView) {
            super(itemView);
        }
    }
}
