package com.mouxuejie.ccvideo.model.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangxinghe on 12/9/2018.
 */

public class FeedEntity implements Serializable {
    @SerializedName("nextPageUrl")
    public String mNextPageUrl;
    @SerializedName("nextPublishTime")
    public long mNextPublishTime;
    @SerializedName("newestIssueType")
    public String mNewestIssueType;
    @SerializedName("dialog")
    public String mDialog;
    @SerializedName("issueList")
    public List<IssueListEntity> mIssueList;

    public static class IssueListEntity implements Serializable {
        @SerializedName("releaseTime")
        public long mReleaseTime;
        @SerializedName("type")
        public String mType;
        @SerializedName("date")
        public long mDate;
        @SerializedName("publishTime")
        public long mPublishTime;
        @SerializedName("count")
        public int mCount;
        @SerializedName("itemList")
        public List<ItemListEntity> mItemList;

        public static class ItemListEntity implements Serializable {
            @SerializedName("type")
            public String mType;
            @SerializedName("data")
            public DataEntity mData;
            @SerializedName("tag")
            public String mTag;
            @SerializedName("id")
            public int mId;
            @SerializedName("adIndex")
            public int mAdIndex;

            public static class DataEntity implements Serializable {
                @SerializedName("dataType")
                public String mDataType;
                @SerializedName("id")
                public int mId;
                @SerializedName("title")
                public String mTitle;
                @SerializedName("description")
                public String mDescription;
                @SerializedName("library")
                public String mLibrary;
                @SerializedName("consumption")
                public ConsumptionEntity mConsumption;
                @SerializedName("resourceType")
                public String mResourceType;
                @SerializedName("slogan")
                public String mSlogan;
                @SerializedName("provider")
                public ProviderEntity mProvider;
                @SerializedName("category")
                public String mCategory;
                @SerializedName("author")
                public AuthorEntity mAuthor;
                @SerializedName("cover")
                public CoverEntity mCover;
                @SerializedName("playUrl")
                public String mPlayUrl;
                @SerializedName("thumbPlayUrl")
                public String mThumbPlayUrl;
                @SerializedName("duration")
                public int mDuration;
                @SerializedName("webUrl")
                public WebUrlEntity mWebUrl;
                @SerializedName("releaseTime")
                public long mReleaseTime;
                @SerializedName("campaign")
                public String mCampaign;
                @SerializedName("waterMarks")
                public String mWaterMarks;
                @SerializedName("adTrack")
                public String mAdTrack;
                @SerializedName("type")
                public String mType;
                @SerializedName("titlePgc")
                public String mTitlePgc;
                @SerializedName("descriptionPgc")
                public String mDescriptionPgc;
                @SerializedName("remark")
                public String mRemark;
                @SerializedName("ifLimitVideo")
                public boolean mIfLimitVideo;
                @SerializedName("searchWeight")
                public int mSearchWeight;
                @SerializedName("idx")
                public int mIdx;
                @SerializedName("shareAdTrack")
                public String mShareAdTrack;
                @SerializedName("favoriteAdTrack")
                public String mFavoriteAdTrack;
                @SerializedName("webAdTrack")
                public String mWebAdTrack;
                @SerializedName("date")
                public long mDate;
                @SerializedName("promotion")
                public String mPromotion;
                @SerializedName("label")
                public String mLabel;
                @SerializedName("descriptionEditor")
                public String mDescriptionEditor;
                @SerializedName("collected")
                public boolean mCollected;
                @SerializedName("played")
                public boolean mPlayed;
                @SerializedName("lastViewTime")
                public long mLastViewTime;
                @SerializedName("playlists")
                public List<String> mPlaylists;
                @SerializedName("src")
                public String mSrc;
                @SerializedName("tags")
                public List<TagsEntity> mTags;
                @SerializedName("playInfo")
                public List<PlayInfoEntity> mPlayInfo;
                @SerializedName("labelList")
                public List<?> mLabelList;
                @SerializedName("subtitles")
                public List<?> mSubtitles;

                public static class ConsumptionEntity implements Serializable {
                    @SerializedName("collectionCount")
                    public int mCollectionCount;
                    @SerializedName("shareCount")
                    public int mShareCount;
                    @SerializedName("replyCount")
                    public int mReplyCount;
                }

                public static class ProviderEntity implements Serializable {
                    @SerializedName("name")
                    public String mName;
                    @SerializedName("alias")
                    public String mAlias;
                    @SerializedName("icon")
                    public String mIcon;
                }

                public static class AuthorEntity implements Serializable {
                    @SerializedName("id")
                    public int mId;
                    @SerializedName("icon")
                    public String mIcon;
                    @SerializedName("name")
                    public String mName;
                    @SerializedName("description")
                    public String mDescription;
                    @SerializedName("link")
                    public String mLink;
                    @SerializedName("latestReleaseTime")
                    public long mLatestReleaseTime;
                    @SerializedName("videoNum")
                    public int mVideoNum;
                    @SerializedName("adTrack")
                    public String mAdTrack;
                    @SerializedName("follow")
                    public FollowEntity mFollow;
                    @SerializedName("shield")
                    public ShieldEntity mShield;
                    @SerializedName("approvedNotReadyVideoCount")
                    public int mApprovedNotReadyVideoCount;
                    @SerializedName("ifPgc")
                    public boolean mIfPgc;

                    public static class FollowEntity implements Serializable {
                        @SerializedName("itemType")
                        public String mItemType;
                        @SerializedName("itemId")
                        public int mItemId;
                        @SerializedName("followed")
                        public boolean mFollowed;
                    }

                    public static class ShieldEntity implements Serializable {
                        @SerializedName("itemType")
                        public String mItemType;
                        @SerializedName("itemId")
                        public int mItemId;
                        @SerializedName("shielded")
                        public boolean mShielded;
                    }
                }

                public static class CoverEntity implements Serializable {
                    @SerializedName("feed")
                    public String mFeed;
                    @SerializedName("detail")
                    public String mDetail;
                    @SerializedName("blurred")
                    public String mBlurred;
                    @SerializedName("sharing")
                    public String mSharing;
                    @SerializedName("homepage")
                    public String mHomepage;
                }

                public static class WebUrlEntity implements Serializable {
                    @SerializedName("raw")
                    public String mRaw;
                    @SerializedName("forWeibo")
                    public String mForWeibo;
                }

                public static class TagsEntity implements Serializable {
                    @SerializedName("id")
                    public int mId;
                    @SerializedName("name")
                    public String mName;
                    @SerializedName("actionUrl")
                    public String mActionUrl;
                    @SerializedName("adTrack")
                    public String mAdTrack;
                    @SerializedName("desc")
                    public String mDesc;
                    @SerializedName("bgPicture")
                    public String mBgPicture;
                    @SerializedName("headerImage")
                    public String mHeaderImage;
                    @SerializedName("tagRecType")
                    public String mTagRecType;
                    @SerializedName("childTagList")
                    public List<String> mChildTagList;
                    @SerializedName("childTagIdList")
                    public List<String> mChildTagIdList;
                }

                public static class PlayInfoEntity implements Serializable {
                    @SerializedName("height")
                    public int mHeight;
                    @SerializedName("width")
                    public int mWidth;
                    @SerializedName("name")
                    public String mName;
                    @SerializedName("type")
                    public String mType;
                    @SerializedName("url")
                    public String mUrl;
                    @SerializedName("urlList")
                    public List<UrlListEntity> mUrlList;

                    public static class UrlListEntity implements Serializable {
                        @SerializedName("name")
                        public String mName;
                        @SerializedName("url")
                        public String mUrl;
                        @SerializedName("size")
                        public int mSize;
                    }
                }
            }
        }
    }
}
