package com.example.suellencolangelo.tecnonutriconsumer.author.authorDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.suellencolangelo.tecnonutriconsumer.R;
import com.example.suellencolangelo.tecnonutriconsumer.base.fragment.BaseFragment;
import com.example.suellencolangelo.tecnonutriconsumer.feed.detail.FeedDetailsFragment;
import com.example.suellencolangelo.tecnonutriconsumer.feed.feeds.FeedsFragment;

/**
 * Created by suellencolangelo on 26/02/17.
 */

public class AuthorDetailFragment extends BaseFragment {
    public static final String TAG = "AUTHOR_DETAILS_FRAGMENT";

    public static AuthorDetailFragment newInstance(Bundle bundle) {
        AuthorDetailFragment fragment = new AuthorDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.author_details_fragment, container, false);


        FeedsFragment feedsFragment = FeedsFragment.newInstance(true);
        openFragment(R.id.author_details_feeds_container, feedsFragment);
        return root;
    }


    private void openFragment(int container, Fragment frag) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(container, frag).commit();
    }

}
