package com.example.CSDNCLient.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import com.example.CSDNCLient.R;
import com.example.CSDNCLient.TestActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by never on 2014/8/26.
 */
public class DrawerView implements OnClickListener {
    private final Activity activity;
    private SlidingMenu slidingMenu;
    private RelativeLayout setting_btn;

    public DrawerView(Activity activity) {
        this.activity = activity;
    }

    public SlidingMenu initSlidingMenu() {
        slidingMenu = new SlidingMenu(activity);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.attachToActivity(activity, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.left_drawer);

        slidingMenu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
            @Override
            public void onOpened() {

            }
        });
        initView();
        return slidingMenu;
    }

    private void initView() {
        setting_btn = (RelativeLayout) slidingMenu.findViewById(R.id.setting_btn);
        setting_btn.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_btn:
                activity.startActivity(new Intent(activity, TestActivity.class));
                break ;
            default:
                break;
        }
    }
}
