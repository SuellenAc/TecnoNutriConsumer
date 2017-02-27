package com.example.suellencolangelo.tecnonutriconsumer.feed.feeds;

import com.example.suellencolangelo.tecnonutriconsumer.feed.model.Feed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suellencolangelo on 26/02/17.
 */

public class FeedPresenter implements FeedContract.Presenter {
    private List<Feed> mFeeds;
    private FeedContract.View mView;

    public FeedPresenter(FeedContract.View view) {
        this.mView = view;
        mFeeds = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mFeeds.add(new Feed());
        }
    }

    @Override
    public int feedCount() {
        if (mFeeds != null) {
            return mFeeds.size();
        }
        return 0;
    }

    @Override
    public Feed getFeed(int position) {
        if (mFeeds != null) {
            return mFeeds.get(position);
        }
        return null;
    }

    @Override
    public void onAuthorClick(int position) {
        mView.openAuthor(getFeed(position).getAuthor());
    }

    @Override
    public void onFeedClick(int position) {
        mView.openFeedDetail(getFeed(position));
    }
}
