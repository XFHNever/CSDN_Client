package com.example.CSDNCLient.view;

import android.app.Activity;
import com.example.CSDNCLient.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by never on 2014/8/26.
 */
public class DrawerView {
    private Activity activity;
    private SlidingMenu slidingMenu;
    public DrawerView(Activity activity) {
        this.activity = activity;
    }

    public SlidingMenu initSlidingMenu() {
        slidingMenu = new SlidingMenu(activity);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.SLIDING_WINDOW);
        slidingMenu.setTouchModeBehind(SlidingMenu.RIGHT);
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.attachToActivity(activity, SlidingMenu.RIGHT);
        slidingMenu.setMenu(R.layout.left_drawer);
        slidingMenu.setSecondaryMenu(R.layout.left_drawer);
        slidingMenu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
            @Override
            public void onOpened() {

            }
        });

        return slidingMenu;
    }
}
