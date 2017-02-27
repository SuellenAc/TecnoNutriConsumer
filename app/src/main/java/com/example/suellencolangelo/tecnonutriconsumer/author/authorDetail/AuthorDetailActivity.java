package com.example.suellencolangelo.tecnonutriconsumer.author.authorDetail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.suellencolangelo.tecnonutriconsumer.R;
import com.example.suellencolangelo.tecnonutriconsumer.base.activity.BaseActivity;

/**
 * Created by suellencolangelo on 27/02/17.
 */

public class AuthorDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);

        AuthorDetailFragment authorDetailsFragment = AuthorDetailFragment.newInstance(getIntent().getExtras());
        openFragment(authorDetailsFragment, AuthorDetailFragment.TAG, R.id.main_fragment_container);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.author_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
