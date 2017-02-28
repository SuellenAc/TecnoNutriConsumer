package com.example.suellencolangelo.tecnonutriconsumer;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.suellencolangelo.tecnonutriconsumer.base.activity.BaseActivity;
import com.example.suellencolangelo.tecnonutriconsumer.item.items.ItemsFragment;

public class MainActivity extends BaseActivity {
    ItemsFragment mItemsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);

        mItemsFragment = ItemsFragment.newInstance(false);
        openFragment(mItemsFragment, ItemsFragment.TAG, R.id.main_fragment_container);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.feeds);
    }
}
