package com.example.CSDNCLient.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

/**
 * Created by never on 2014/8/28.
 */
public class ColumnHorizontalScrollView extends HorizontalScrollView {
    private View content_view;
    private View column_view;
    private ImageView left_image;
    private ImageView right_image;
    private int mScreenWidth = 0;
    private Activity activity;

    public ColumnHorizontalScrollView(Context context) {
        super(context);
    }

    public ColumnHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColumnHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        shade_ShowOrHide();

        if (!activity.isFinishing() && content_view != null && left_image != null
                && right_image != null && column_view != null) {
            if (content_view.getWidth() <= mScreenWidth) {
                left_image.setVisibility(View.GONE);
                right_image.setVisibility(View.GONE);
            }
        } else {
            return;
        }

        if (l == 0) {
            left_image.setVisibility(View.GONE);
            right_image.setVisibility(View.VISIBLE);
            return;
        }

        if (content_view.getWidth() - l + column_view.getLeft() == mScreenWidth) {
            left_image.setVisibility(View.VISIBLE);
            right_image.setVisibility(View.GONE);
            return;
        }
        left_image.setVisibility(View.VISIBLE);
        right_image.setVisibility(View.VISIBLE);
    }

    private void shade_ShowOrHide() {
        if (!activity.isFinishing() && content_view !=null) {
             measure(0, 0);
            if (mScreenWidth >= getMeasuredWidth()) {
                left_image.setVisibility(View.GONE);
                right_image.setVisibility(View.GONE);
            }
        } else {
            return;
        }

        if (getLeft() == 0) {
            left_image.setVisibility(View.GONE);
            right_image.setVisibility(View.VISIBLE);
            return;
        }

        if (getRight() == getMeasuredWidth() - mScreenWidth) {
            left_image.setVisibility(View.VISIBLE);
            right_image.setVisibility(View.GONE);
            return;
        }

        left_image.setVisibility(View.VISIBLE);
        right_image.setVisibility(View.VISIBLE);
    }

    public void setParam(Activity activity, int mScreenWidth, View content,
                         View column, ImageView left, ImageView right) {
        this.activity = activity;
        this.mScreenWidth = mScreenWidth;
        this.content_view = content;
        this.column_view = column;
        this.left_image = left;
        this.right_image = right;
    }
}
