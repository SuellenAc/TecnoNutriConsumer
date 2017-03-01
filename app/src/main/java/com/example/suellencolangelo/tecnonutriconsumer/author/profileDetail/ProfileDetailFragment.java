package com.example.suellencolangelo.tecnonutriconsumer.author.profileDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
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
import com.example.suellencolangelo.tecnonutriconsumer.item.detail.ItemDetailsActivity;
import com.example.suellencolangelo.tecnonutriconsumer.item.items.ItemContract;
import com.example.suellencolangelo.tecnonutriconsumer.item.items.ItemsAdapter;
import com.example.suellencolangelo.tecnonutriconsumer.model.Item;
import com.example.suellencolangelo.tecnonutriconsumer.model.Profile;
import com.example.suellencolangelo.tecnonutriconsumer.utils.glide.GlideCircleTransform;

import org.parceler.Parcels;

/**
 * Created by suellencolangelo on 26/02/17.
 */

public class ProfileDetailFragment extends BaseFragment implements ProfileDetailContract.View, SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = "AUTHOR_DETAILS_FRAGMENT";

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeLayout;
    private GridLayoutManager mLayoutManager;
    private ProfileDetailAdapter mAdapter;

    private TextView mAuthorName;
    private TextView mAuthorGoal;
    private ImageView mAuthorImage;

    private ProfileDetailContract.Presenter mPresenter;
    private boolean loadingMore;

    public static ProfileDetailFragment newInstance(Bundle bundle) {
        ProfileDetailFragment fragment = new ProfileDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.profile_details_fragment, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.author_recycler_view);
        mAuthorName = (TextView) root.findViewById(R.id.author_name);
        mAuthorGoal = (TextView) root.findViewById(R.id.author_goal);
        mAuthorImage = (ImageView) root.findViewById(R.id.author_image);
        mSwipeLayout = (SwipeRefreshLayout) root.findViewById(R.id.author_swipe_refresh_layout);
        mSwipeLayout.setOnRefreshListener(this);
        loadingMore = true;

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Profile profile;
        if (getArguments() != null) {
            profile = Parcels.unwrap(getArguments().getParcelable(ProfileDetailActivity.PROFILE));
        } else {
            profile = new Profile();
        }
        mPresenter = new ProfileDetailPresenter(profile, this);

        reloadData(profile);
        configureRecyclerView();
        mPresenter.retrieveProfile();
    }

    private void configureRecyclerView() {
        mLayoutManager = new GridLayoutManager(getContext(), 4);
        mAdapter = new ProfileDetailAdapter(mPresenter);
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
                        mPresenter.retrieveOldProfileItems();
                    }
                }
            }
        });
    }


    public void reloadData(Profile profile) {
        mAuthorName.setText(profile.getName());
        // Meta do Author
        mAuthorGoal.setText(profile.getGeneralGoal());
        // image de perfil do Author
        Glide.with(getContext())
                .load(profile.getImage())
                .transform(new GlideCircleTransform(getContext()))
                .placeholder(R.drawable.circular_border)
                .error(R.drawable.circular_border)
                .into(mAuthorImage);
    }

    @Override
    public void openFeedDetail(Item item) {
        // Abre os detalhes do feed.
        Intent i = new Intent(getContext(), ItemDetailsActivity.class);
        i.putExtra(ItemDetailsActivity.FEED, Parcels.wrap(item));
        startActivity(i);
    }

    @Override
    public void onItemsRetrieved() {
        // Atualiza os dados.
        loadingMore = true;
        mSwipeLayout.setRefreshing(false);
        reloadData(mPresenter.getProfile());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure() {
        // Mostra mensagem de erro
        loadingMore = false;
        showDownloadErrorToast();
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        loadingMore = false;
        mSwipeLayout.setRefreshing(true);
        mPresenter.retrieveProfile();
    }
}
