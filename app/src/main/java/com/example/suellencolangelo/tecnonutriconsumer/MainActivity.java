package com.example.suellencolangelo.tecnonutriconsumer;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.suellencolangelo.tecnonutriconsumer.base.activity.BaseActivity;
import com.example.suellencolangelo.tecnonutriconsumer.feed.feeds.FeedsFragment;

public class MainActivity extends BaseActivity {
    FeedsFragment mFeedsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);

        mFeedsFragment = FeedsFragment.newInstance(false);
        openFragment(mFeedsFragment, FeedsFragment.TAG, R.id.main_fragment_container);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.feeds);
    }
}
