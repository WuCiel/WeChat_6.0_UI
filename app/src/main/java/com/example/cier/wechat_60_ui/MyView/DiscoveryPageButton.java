package com.example.cier.wechat_60_ui.MyView;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Cier on 2017/10/9.
 */

public class DiscoveryPageButton extends Button {

    private Bitmap iconleft,iconRight;
    private String text;
    private int textSize,textColor;

    public DiscoveryPageButton(Context context) {
        this(context,null);
    }

    public DiscoveryPageButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DiscoveryPageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
