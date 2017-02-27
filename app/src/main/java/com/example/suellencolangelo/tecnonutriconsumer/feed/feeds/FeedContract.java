package com.example.suellencolangelo.tecnonutriconsumer.feed.feeds;

import com.example.suellencolangelo.tecnonutriconsumer.author.model.Author;
import com.example.suellencolangelo.tecnonutriconsumer.feed.model.Feed;

/**
 * Created by suellencolangelo on 26/02/17.
 */

public interface FeedContract {

    interface View {
        void openAuthor(Author author);
        void openFeedDetail(Feed feed);
    }

    // Implementado pelo Presenter
    interface Presenter {
        int feedCount();
        Feed getFeed(int position);
        void onAuthorClick ( int position);
        void onFeedClick ( int position);
    }
}
