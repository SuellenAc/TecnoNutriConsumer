package com.example.suellencolangelo.tecnonutriconsumer.item.items;

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
import com.example.suellencolangelo.tecnonutriconsumer.model.Item;
import com.example.suellencolangelo.tecnonutriconsumer.model.Profile;
import com.example.suellencolangelo.tecnonutriconsumer.base.fragment.BaseFragment;
import com.example.suellencolangelo.tecnonutriconsumer.item.detail.ItemDetailsActivity;

import org.parceler.Parcels;

/**
 * Created by suellencolangelo on 26/02/17.
 */

public class ItemsFragment extends BaseFragment implements ItemContract.View{
    public static final String TAG = "FEEDS_FRAGMENT";
    private static final String SHOW_AS_GRID = "SHOW_AS_GRID";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ItemsAdapter mAdapter;

    private ItemContract.Presenter mPresenter;

    private Boolean mShowAsGrid;


    public static ItemsFragment newInstance(Boolean showAsGrid) {
        ItemsFragment fragment = new ItemsFragment();
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
        mPresenter = new ItemPresenter(this);
        mPresenter.retrieveItems();

        View root = inflater.inflate(R.layout.items_fragment, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.feeds_recycler_view);

        configureRecyclerView();

        return root;
    }

    private void configureRecyclerView() {
        if (mShowAsGrid) {
            mLayoutManager = new GridLayoutManager(getContext(), 4);
        } else {
            mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        }
        mAdapter = new ItemsAdapter(mShowAsGrid, mPresenter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void openAuthor(Profile profile) {
        Intent i = new Intent(getContext(), AuthorDetailActivity.class);
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
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCallFailure() {

    }
}
