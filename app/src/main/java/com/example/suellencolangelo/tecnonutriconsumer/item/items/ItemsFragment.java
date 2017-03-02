package com.example.suellencolangelo.tecnonutriconsumer.item.items;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.suellencolangelo.tecnonutriconsumer.R;
import com.example.suellencolangelo.tecnonutriconsumer.author.profileDetail.ProfileDetailActivity;
import com.example.suellencolangelo.tecnonutriconsumer.model.Item;
import com.example.suellencolangelo.tecnonutriconsumer.model.Profile;
import com.example.suellencolangelo.tecnonutriconsumer.base.fragment.BaseFragment;
import com.example.suellencolangelo.tecnonutriconsumer.item.detail.ItemDetailsActivity;

import org.parceler.Parcels;

/**
 * Created by suellencolangelo on 26/02/17.
 */

public class ItemsFragment extends BaseFragment implements ItemContract.View, SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = "FEEDS_FRAGMENT";

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ItemsAdapter mAdapter;
    private SwipeRefreshLayout mSwipeLayout;
    private View mLoadingMore;
    private TextView mNoData;

    private ItemContract.Presenter mPresenter;
    private boolean loadingMore;


    public static ItemsFragment newInstance() {
        ItemsFragment fragment = new ItemsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.items_fragment, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.feeds_recycler_view);
        mSwipeLayout = (SwipeRefreshLayout) root.findViewById(R.id.feeds_swipe_refresh_layout);
        mLoadingMore = root.findViewById(R.id.feeds_loading_more);
        mNoData = (TextView) root.findViewById(R.id.feeds_no_data_message);
        mSwipeLayout.setOnRefreshListener(this);
        mPresenter = new ItemPresenter(this);
        loadingMore = true;
        configureRecyclerView();
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.retrieveItems();
    }

    private void configureRecyclerView() {
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new ItemsAdapter(mPresenter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastPosition = mLayoutManager.findLastCompletelyVisibleItemPosition();
                if (dy != 0 && loadingMore) {
                    if (lastPosition == mPresenter.itemCount() - 1) {
                        loadingMore = false;
                        mPresenter.retrieveOldItems();
                    }
                }
            }
        });
    }

    @Override
    public void openAuthor(Profile profile) {
        Intent i = new Intent(getContext(), ProfileDetailActivity.class);
        i.putExtra(ProfileDetailActivity.PROFILE, Parcels.wrap(profile));
        startActivity(i);
    }

    @Override
    public void openFeedDetail(Item item) {
        Intent i = new Intent(getContext(), ItemDetailsActivity.class);
        i.putExtra(ItemDetailsActivity.FEED, Parcels.wrap(item));
        startActivity(i);
    }

    @Override
    public void onItemsRetrieved() {
        loadingMore = true;
        mSwipeLayout.setRefreshing(false);
        mLoadingMore.setVisibility(View.GONE);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure() {
        loadingMore = false;
        mLoadingMore.setVisibility(View.GONE);
        mSwipeLayout.setRefreshing(false);
        showDownloadErrorToast();
    }

    @Override
    public void showNoDataView() {
        mNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoDataView() {
        mNoData.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        loadingMore = false;
        mSwipeLayout.setRefreshing(true);
        mPresenter.retrieveItems();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.setView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.setView(null);
    }
}
