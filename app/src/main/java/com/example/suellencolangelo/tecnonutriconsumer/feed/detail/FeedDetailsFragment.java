package com.example.suellencolangelo.tecnonutriconsumer.feed.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.suellencolangelo.tecnonutriconsumer.R;
import com.example.suellencolangelo.tecnonutriconsumer.base.fragment.BaseFragment;

/**
 * Created by suellencolangelo on 26/02/17.
 */

public class FeedDetailsFragment extends BaseFragment {
    public static final String TAG = "FEED_DETAILS_FRAGMENT";

    public static FeedDetailsFragment newInstance(Bundle bundle) {
        FeedDetailsFragment fragment = new FeedDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.feed_details_fragment, container, false);
        return root;
    }
}
