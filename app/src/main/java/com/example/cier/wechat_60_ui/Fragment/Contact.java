package com.example.cier.wechat_60_ui.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cier.wechat_60_ui.R;

/**
 * Created by Cier on 2017/10/6.
 */

public class Contact extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_contact,container,false);
        return view;
    }
}
