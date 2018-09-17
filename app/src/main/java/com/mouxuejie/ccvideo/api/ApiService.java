package com.mouxuejie.ccvideo.api;

import com.mouxuejie.ccvideo.model.bean.FeedEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by wangxinghe on 11/9/2018.
 */

public interface ApiService {

    @GET("v2/feed")
    Observable<FeedEntity> getFirstPageFeedList();

    @GET
    Observable<FeedEntity> getNextPageFeedList(@Url String nextPageUrl);
}
