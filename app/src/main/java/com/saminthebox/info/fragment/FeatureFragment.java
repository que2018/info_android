package com.saminthebox.info.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.saminthebox.info.customview.LoadingButton;
import com.saminthebox.info.R;

public class FeatureFragment extends Fragment {

    private ProgressBar progressBar;
    private ListView tradeList;
    private EditText priceEditText;
    private EditText amountEditText;
    private LoadingButton submitButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feature, null);

        return rootView;
    }
}
