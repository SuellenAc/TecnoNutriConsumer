package com.example.suellencolangelo.tecnonutriconsumer.feed.feeds;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.suellencolangelo.tecnonutriconsumer.R;
import com.example.suellencolangelo.tecnonutriconsumer.author.authorDetail.AuthorDetailActivity;
import com.example.suellencolangelo.tecnonutriconsumer.author.model.Author;
import com.example.suellencolangelo.tecnonutriconsumer.base.fragment.BaseFragment;
import com.example.suellencolangelo.tecnonutriconsumer.feed.detail.FeedDetailsActivity;
import com.example.suellencolangelo.tecnonutriconsumer.feed.model.Feed;

/**
 * Created by suellencolangelo on 26/02/17.
 */

public class FeedsFragment extends BaseFragment implements FeedContract.View{
    public static final String TAG = "FEEDS_FRAGMENT";
    private static final String SHOW_AS_GRID = "SHOW_AS_GRID";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private FeedsAdapter mAdapter;

    private FeedContract.Presenter mPresenter;

    private Boolean mShowAsGrid;


    public static FeedsFragment newInstance(Boolean showAsGrid) {
        FeedsFragment fragment = new FeedsFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(SHOW_AS_GRID, showAsGrid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (getArguments() != null) {
            mShowAsGrid = getArguments().getBoolean(SHOW_AS_GRID, false);
        }
        mPresenter = new FeedPresenter(this);

        View root = inflater.inflate(R.layout.feeds_fragment, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.feeds_recycler_view);

        configureRecyclerView();

        return root;
    }

    private void configureRecyclerView() {
        if (mShowAsGrid) {
            mLayoutManager = new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false);
        } else {
            mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        }
        mAdapter = new FeedsAdapter(mShowAsGrid, mPresenter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void openAuthor(Author author) {
        Intent i = new Intent(getContext(), AuthorDetailActivity.class);
        startActivity(i);
    }

    @Override
    public void openFeedDetail(Feed feed) {
        Intent i = new Intent(getContext(), FeedDetailsActivity.class);
        startActivity(i);
    }
}
