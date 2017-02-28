package com.example.suellencolangelo.tecnonutriconsumer.item.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.suellencolangelo.tecnonutriconsumer.R;
import com.example.suellencolangelo.tecnonutriconsumer.base.fragment.BaseFragment;
import com.example.suellencolangelo.tecnonutriconsumer.model.Item;
import com.example.suellencolangelo.tecnonutriconsumer.utils.glide.GlideCircleTransform;

import org.parceler.Parcels;

/**
 * Created by suellencolangelo on 26/02/17.
 */

public class ItemDetailsFragment extends BaseFragment implements ItemDetailsContract.View, SwipeRefreshLayout.OnRefreshListener{
    public static final String TAG = "FEED_DETAILS_FRAGMENT";

    private TextView mAuthorName;
    private TextView mAuthorGoal;
    private ImageView mAuthorImage;
    private TextView mFeedKcal;
    private TextView mDate;
    private ImageView mImage;
    private SwipeRefreshLayout mSwipeLayout;

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
        mAuthorName = (TextView) root.findViewById(R.id.feed_detail_author_name);
        mAuthorGoal = (TextView) root.findViewById(R.id.feed_detail_author_goal);
        mAuthorImage = (ImageView) root.findViewById(R.id.feed_detail_author_image);
        mFeedKcal = (TextView) root.findViewById(R.id.feed_detail_kcal);
        mDate = (TextView) root.findViewById(R.id.feed_detail_date);
        mImage = (ImageView) root.findViewById(R.id.feed_detail_image);
        mSwipeLayout = (SwipeRefreshLayout) root.findViewById(R.id.feed_detail_swipe_refresh_layout);
        mSwipeLayout.setOnRefreshListener(this);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Obtem o item
        Item item = null;
        if (getArguments()!=null){
            item = Parcels.unwrap(getArguments().getParcelable(ItemDetailsActivity.FEED));
            reloadData(item);
        }
        mPresenter = new ItemDetailsPresenter(item, this);
        mPresenter.updateItem();
    }

    @Override
    public void reloadData(Item item) {
        if (item==null){
            return;
        }
        mSwipeLayout.setRefreshing(false);

        mAuthorName.setText(item.getProfile().getName());
        // Meta do Author
        mAuthorGoal.setText(item.getProfile().getGeneralGoal());
        // image de perfil do Author
        Glide.with(getContext())
                .load(item.getProfile().getImage())
                .transform(new GlideCircleTransform(getContext()))
                .into(mAuthorImage);

        // Dados do item
        //Data
        mDate.setText(item.getDate());
        // image do prato
        Glide.with(mImage.getContext())
                .load(item.getImage())
                .crossFade()
                .centerCrop()
                .into(mImage);
        // kcal
        mFeedKcal.setText(Float.toString(item.getCarbohydrate()));
    }

    @Override
    public void showError() {
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mSwipeLayout.setRefreshing(true);
        mPresenter.updateItem();
    }
}
