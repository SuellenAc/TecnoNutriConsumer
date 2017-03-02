package com.example.suellencolangelo.tecnonutriconsumer.item.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.suellencolangelo.tecnonutriconsumer.R;
import com.example.suellencolangelo.tecnonutriconsumer.base.fragment.BaseFragment;
import com.example.suellencolangelo.tecnonutriconsumer.model.Item;
import com.example.suellencolangelo.tecnonutriconsumer.utils.DateUtils;
import com.example.suellencolangelo.tecnonutriconsumer.utils.glide.GlideCircleTransform;

import org.parceler.Parcels;

/**
 * Created by suellencolangelo on 26/02/17.
 */

public class ItemDetailsFragment extends BaseFragment implements ItemDetailsContract.View, SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = "FEED_DETAILS_FRAGMENT";

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ItemDetailsAdapter mAdapter;
    private SwipeRefreshLayout mSwipeLayout;
    private View mLoadingData;

    private ItemDetailsContract.Presenter mPresenter;

    public static ItemDetailsFragment newInstance(Bundle bundle) {
        ItemDetailsFragment fragment = new ItemDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.item_details_fragment, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.feed_recycler_view);
        mLoadingData = root.findViewById(R.id.feeds_loading_more);
        mSwipeLayout = (SwipeRefreshLayout) root.findViewById(R.id.feed_detail_swipe_refresh_layout);
        mSwipeLayout.setOnRefreshListener(this);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Obtem o item
        Item item = null;
        if (getArguments() != null) {
            item = Parcels.unwrap(getArguments().getParcelable(ItemDetailsActivity.FEED));
        }
        mPresenter = new ItemDetailsPresenter(item, this);
        mPresenter.updateItem();
        configureRecyclerView();
        reloadData(item);

    }

    private void configureRecyclerView() {
        mAdapter = new ItemDetailsAdapter(mPresenter);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    @Override
    public void reloadData(Item item) {
        if (item == null) {
            return;
        }
        mSwipeLayout.setRefreshing(false);
        mAdapter.notifyDataSetChanged();
        mLoadingData.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        showDownloadErrorToast();
        mLoadingData.setVisibility(View.GONE);
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mSwipeLayout.setRefreshing(true);
        mPresenter.updateItem();
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
