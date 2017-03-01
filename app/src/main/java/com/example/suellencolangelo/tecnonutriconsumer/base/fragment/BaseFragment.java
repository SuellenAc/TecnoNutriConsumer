package com.example.suellencolangelo.tecnonutriconsumer.base.fragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.suellencolangelo.tecnonutriconsumer.R;

/**
 * Created by suellencolangelo on 26/02/17.
 */

public class BaseFragment extends Fragment {

    protected void showDownloadErrorToast() {
        if (isAdded() && isVisible()) {
            Toast toast = Toast.makeText(getContext(), R.string.error, Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
