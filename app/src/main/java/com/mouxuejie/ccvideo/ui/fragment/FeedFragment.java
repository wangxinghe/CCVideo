package com.mouxuejie.ccvideo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mouxuejie.ccvideo.R;
import com.mouxuejie.ccvideo.adapter.FeedAdapter;
import com.mouxuejie.ccvideo.api.ApiService;
import com.mouxuejie.ccvideo.frame.ui.adapterview.HeaderAndFooterRecyclerView;
import com.mouxuejie.ccvideo.frame.ui.adapterview.LoadMoreFooter;
import com.mouxuejie.ccvideo.model.bean.FeedEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangxinghe on 12/9/2018.
 */

public class FeedFragment extends Fragment {
    private static final String TAG = "FeedFragment";

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view)
    HeaderAndFooterRecyclerView mRecyclerView;

    private LoadMoreFooter mLoadMoreFooter;

    private FeedAdapter mFeedAdapter;
    private FeedEntity mFeedEntity;
    private List<FeedEntity.IssueListEntity.ItemListEntity> mItemListEntities = new ArrayList<>();

    public static FeedFragment newInstance() {

        Bundle args = new Bundle();

        FeedFragment fragment = new FeedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.feed_fragment, container, false);
        ButterKnife.bind(this, contentView);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new OnRecyclerViewRefreshListener());
        mSwipeRefreshLayout.setOnChildScrollUpCallback(new SwipeRefreshLayout.OnChildScrollUpCallback() {
            @Override
            public boolean canChildScrollUp(@NonNull SwipeRefreshLayout parent, @Nullable View child) {
                return false;
            }
        });
        mSwipeRefreshLayout.setRefreshing(true);
        mLoadMoreFooter = new LoadMoreFooter(getContext(), mRecyclerView, new OnRecyclerViewLoadMoreListener());
        mFeedAdapter = new FeedAdapter(getActivity(), mItemListEntities);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mFeedAdapter);

        return contentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadFirstPageFeedListData();
    }

    private void loadFirstPageFeedListData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://baobab.kaiyanapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        apiService.getFirstPageFeedList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FeedEntity>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        Log.d(TAG, "loadFirstPageFeedListData onSubscribe");
                        mSwipeRefreshLayout.setRefreshing(true);
                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull FeedEntity feedEntity) {
                        Log.d(TAG, "loadFirstPageFeedListData onNext " + feedEntity);

                        mFeedEntity = feedEntity;
                        mItemListEntities.clear();
                        mItemListEntities.addAll(feedEntity.mIssueList.get(0).mItemList);
                        mFeedAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        e.printStackTrace();
                        mSwipeRefreshLayout.setRefreshing(false);
                        mLoadMoreFooter.setState(LoadMoreFooter.STATE_FAILED);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "loadFirstPageFeedListData onComplete");
                        mSwipeRefreshLayout.setRefreshing(false);
                        mLoadMoreFooter.setState(LoadMoreFooter.STATE_ENDLESS);
                    }
                });
    }

    private void loadNextPageFeedListData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://baobab.kaiyanapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getNextPageFeedList(mFeedEntity.mNextPageUrl).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FeedEntity>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        Log.d(TAG, "loadNextPageFeedListData onSubscribe");
                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull FeedEntity feedEntity) {
                        Log.d(TAG, "loadNextPageFeedListData onNext " + feedEntity);

                        mFeedEntity = feedEntity;
                        mItemListEntities.addAll(feedEntity.mIssueList.get(0).mItemList);
                        mFeedAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        e.printStackTrace();
                        mSwipeRefreshLayout.setRefreshing(false);
                        mLoadMoreFooter.setState(LoadMoreFooter.STATE_FAILED);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "loadNextPageFeedListData onComplete");
                        mSwipeRefreshLayout.setRefreshing(false);
                        mLoadMoreFooter.setState(TextUtils.isEmpty(mFeedEntity.mNextPageUrl)
                                ? LoadMoreFooter.STATE_FINISHED : LoadMoreFooter.STATE_ENDLESS);
                    }
                });
    }

    private class OnRecyclerViewRefreshListener implements SwipeRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh() {
            loadFirstPageFeedListData();
        }

    }

    private class OnRecyclerViewLoadMoreListener implements LoadMoreFooter.OnLoadMoreListener {

        @Override
        public void onLoadMore() {
            loadNextPageFeedListData();
        }
    }

}
