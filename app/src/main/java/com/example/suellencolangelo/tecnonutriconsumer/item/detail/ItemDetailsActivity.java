package com.example.suellencolangelo.tecnonutriconsumer.item.detail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.suellencolangelo.tecnonutriconsumer.R;
import com.example.suellencolangelo.tecnonutriconsumer.base.activity.BaseActivity;


/**
 * Created by suellencolangelo on 27/02/17.
 */

public class ItemDetailsActivity extends BaseActivity {
    public static final String FEED = "FEED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);

        ItemDetailsFragment itemDetailsFragment = ItemDetailsFragment.newInstance(getIntent().getExtras());
        openFragment(itemDetailsFragment, ItemDetailsFragment.TAG, R.id.main_fragment_container);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.feed_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

}
