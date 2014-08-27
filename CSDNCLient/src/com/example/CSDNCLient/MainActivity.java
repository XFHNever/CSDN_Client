package com.example.CSDNCLient;

import android.app.Activity;
import android.os.Bundle;
import com.example.CSDNCLient.view.DrawerView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private SlidingMenu side_drawer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initSlidingMenu();
    }

    private void initSlidingMenu() {
        side_drawer = new DrawerView(this).initSlidingMenu();
    }


}
