package com.mouxuejie.ccvideo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.mouxuejie.ccvideo.R;
import com.mouxuejie.ccvideo.model.bean.FeedEntity;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangxinghe on 12/9/2018.
 */

public class VideoDetailActivity extends AppCompatActivity {
    private static final String TAG = "VideoDetailActivity";
    private static final String KEY_ITEM_LIST_ENTITY = "ItemListEntity";
    @BindView(R.id.video_view)
    StandardGSYVideoPlayer mVideoPlayer;

    public static void jump(Context context, FeedEntity.IssueListEntity.ItemListEntity itemListEntity) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        intent.putExtra(KEY_ITEM_LIST_ENTITY, itemListEntity);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_detail_activity);
        ButterKnife.bind(this);

        FeedEntity.IssueListEntity.ItemListEntity itemListEntity
                = (FeedEntity.IssueListEntity.ItemListEntity)getIntent().getSerializableExtra(KEY_ITEM_LIST_ENTITY);
        mVideoPlayer.setUp(itemListEntity.mData.mPlayUrl, true, null, null, "这是title");
        mVideoPlayer.startPlayLogic();
        mVideoPlayer.setVideoAllCallBack(new VideoCallBackImpl());
        //增加title
        mVideoPlayer.getTitleTextView().setVisibility(View.GONE);
        //设置返回键
        mVideoPlayer.getBackButton().setVisibility(View.GONE);
        //设置全屏按键功能
        mVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoPlayer.startWindowFullscreen(VideoDetailActivity.this, true, true);
            }
        });
        //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        mVideoPlayer.setRotateViewAuto(false);
        //全屏动画
        mVideoPlayer.setShowFullAnimation(true);
        //小屏时不触摸滑动
        mVideoPlayer.setIsTouchWiget(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getVideoPlayer().onVideoResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getVideoPlayer().onVideoPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mVideoPlayer.setVideoAllCallBack(null);
        GSYVideoManager.releaseAllVideos();
    }

    private GSYVideoPlayer getVideoPlayer() {
        return mVideoPlayer.getFullWindowPlayer() != null ? mVideoPlayer.getFullWindowPlayer() : mVideoPlayer;
    }

    private static class VideoCallBackImpl implements VideoAllCallBack {

        @Override
        public void onStartPrepared(String url, Object... objects) {
            Log.d(TAG, "onStartPrepared");
        }

        @Override
        public void onPrepared(String url, Object... objects) {
            Log.d(TAG, "onPrepared");
        }

        @Override
        public void onClickStartIcon(String url, Object... objects) {
            Log.d(TAG, "onClickStartIcon");
        }

        @Override
        public void onClickStartError(String url, Object... objects) {
            Log.d(TAG, "onClickStartError");
        }

        @Override
        public void onClickStop(String url, Object... objects) {
            Log.d(TAG, "onClickStop");
        }

        @Override
        public void onClickStopFullscreen(String url, Object... objects) {
            Log.d(TAG, "onClickStopFullscreen");
        }

        @Override
        public void onClickResume(String url, Object... objects) {
            Log.d(TAG, "onClickResume");
        }

        @Override
        public void onClickResumeFullscreen(String url, Object... objects) {
            Log.d(TAG, "onClickResumeFullscreen");
        }

        @Override
        public void onClickSeekbar(String url, Object... objects) {
            Log.d(TAG, "onClickSeekbar");
        }

        @Override
        public void onClickSeekbarFullscreen(String url, Object... objects) {
            Log.d(TAG, "onClickSeekbarFullscreen");
        }

        @Override
        public void onAutoComplete(String url, Object... objects) {
            Log.d(TAG, "onAutoComplete");
        }

        @Override
        public void onEnterFullscreen(String url, Object... objects) {
            Log.d(TAG, "onEnterFullscreen");
        }

        @Override
        public void onQuitFullscreen(String url, Object... objects) {
            Log.d(TAG, "onQuitFullscreen");
        }

        @Override
        public void onQuitSmallWidget(String url, Object... objects) {
            Log.d(TAG, "onQuitSmallWidget");
        }

        @Override
        public void onEnterSmallWidget(String url, Object... objects) {
            Log.d(TAG, "onEnterSmallWidget");
        }

        @Override
        public void onTouchScreenSeekVolume(String url, Object... objects) {
            Log.d(TAG, "onTouchScreenSeekVolume");
        }

        @Override
        public void onTouchScreenSeekPosition(String url, Object... objects) {
            Log.d(TAG, "onTouchScreenSeekPosition");
        }

        @Override
        public void onTouchScreenSeekLight(String url, Object... objects) {
            Log.d(TAG, "onTouchScreenSeekLight");
        }

        @Override
        public void onPlayError(String url, Object... objects) {
            Log.d(TAG, "onPlayError");
        }

        @Override
        public void onClickStartThumb(String url, Object... objects) {
            Log.d(TAG, "onClickStartThumb");
        }

        @Override
        public void onClickBlank(String url, Object... objects) {
            Log.d(TAG, "onClickBlank");
        }

        @Override
        public void onClickBlankFullscreen(String url, Object... objects) {
            Log.d(TAG, "onClickBlankFullscreen");
        }
    }
}
