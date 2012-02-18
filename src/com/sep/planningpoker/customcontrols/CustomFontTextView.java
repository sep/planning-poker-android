package com.sep.planningpoker.customcontrols;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomFontTextView extends TextView {

    private final Context mContext;
    private static String mFontFace = "fonts/DejaVuSansCondensedBold.ttf";

    public CustomFontTextView(Context context) {
        super(context);

        mContext = context;
        initializeControl();
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        initializeControl();
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mContext = context;
        initializeControl();
    }

    private void initializeControl() {
        Typeface typeFace = Typeface.createFromAsset(mContext.getAssets(), mFontFace);
        this.setTypeface(typeFace);
    }
}
