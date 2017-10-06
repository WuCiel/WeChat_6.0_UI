package com.example.cier.wechat_60_ui.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cier.wechat_60_ui.R;

/**
 * Created by Cier on 2017/10/6.
 */

public class Me extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_me, container, false);

        return view;
    }
}
