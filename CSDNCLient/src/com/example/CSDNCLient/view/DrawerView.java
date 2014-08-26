package com.example.CSDNCLient.view;

import android.app.Activity;
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

        return slidingMenu;
    }
}
