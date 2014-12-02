package com.example.CSDNCLient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.*;
import android.widget.*;
import com.example.CSDNCLient.adapter.NewsFragmentPagerAdapter;
import com.example.CSDNCLient.fragment.NewsFragment;
import com.example.CSDNCLient.util.BaseTools;
import com.example.CSDNCLient.util.constant.Constants;
import com.example.CSDNCLient.view.ColumnHorizontalScrollView;
import com.example.CSDNCLient.view.DrawerView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    /**
     * Called when the activity is first created.
     */
    //component of head
    private ImageView top_head;
    private TextView top_title;

    //component of ColumnHorizontalScrollView
    private ColumnHorizontalScrollView mColumnHorizontalScrollView;
    private LinearLayout mRadioGroup_content;
    private RelativeLayout news_column;
    private ViewPager mViewPager;
    private ImageView shade_left;
    private ImageView shade_right;

    //Component of slidingMenu
    private SlidingMenu side_drawer;

    private int columnSelectIndex = 0;
    private int mScreenWidth = 0;
    private int mItemWidth = 0;

    //data
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    //exit
    private long mExitTime;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mScreenWidth = BaseTools.getWindowsWidth(this);
        mItemWidth = mScreenWidth/5;
        initView();
        initSlidingMenu();

    }

    private void initView() {
        top_head = (ImageView) findViewById(R.id.top_head);
        top_title = (TextView) findViewById(R.id.top_title);

        mColumnHorizontalScrollView = (ColumnHorizontalScrollView) findViewById(R.id.mColumnHorizontalScrollView);
        mRadioGroup_content = (LinearLayout) findViewById(R.id.mRadioGroup_content);
        news_column = (RelativeLayout) findViewById(R.id.news_column);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        shade_left = (ImageView) findViewById(R.id.shade_left);
        shade_right = (ImageView) findViewById(R.id.shade_right);

        top_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(side_drawer.isMenuShowing()){
                    side_drawer.showContent();
                }else{
                    side_drawer.showMenu();
                }
            }
        });

        setChangeView();
    }

    private void setChangeView() {
        initTabColumn();
        initFragment();
    }

    private void initTabColumn() {
        mRadioGroup_content.removeAllViews();
        int count = Constants.NEW_CLASSIFY.length;
        mColumnHorizontalScrollView.setParam(this, mScreenWidth, mRadioGroup_content,news_column,shade_left, shade_right);

        for (int i = 0; i < count; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;

            TextView columnTextView = new TextView(this);
            columnTextView.setTextAppearance(this, R.style.scrollView_item_text);
            columnTextView.setBackgroundResource(R.drawable.radio_button_bg);
            columnTextView.setGravity(Gravity.CENTER);
            columnTextView.setPadding(5, 5, 5, 5);
            columnTextView.setId(i);
            columnTextView.setText(Constants.NEW_CLASSIFY[i]);
            columnTextView.setTextColor(getResources().getColorStateList(R.color.scroll_view_title_text));

            if (columnSelectIndex == i) {
                columnTextView.setSelected(true);
            }
            columnTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
                        View localView = mRadioGroup_content.getChildAt(i);

                        if (localView != v) {
                            localView.setSelected(false);
                        } else {
                            localView.setSelected(true);
                            mViewPager.setCurrentItem(i);
                        }
                    }
                }
            });

            mRadioGroup_content.addView(columnTextView, i, params);
        }
    }

    private void initFragment() {
        int count = Constants.NEW_CLASSIFY.length;
        for (int i = 0; i < count; i++) {
            Bundle data = new Bundle();
            data.putString("text", Constants.NEW_CLASSIFY[i]);

            NewsFragment newsFragment = new NewsFragment();
            newsFragment.setArguments(data);
            fragments.add(newsFragment);
        }
        NewsFragmentPagerAdapter adapter = new NewsFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(pageListener);
    }

    private void initSlidingMenu() {
        side_drawer = new DrawerView(this).initSlidingMenu();
    }

    public ViewPager.OnPageChangeListener pageListener= new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mViewPager.setCurrentItem(position);
            selectTab(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    private void selectTab(int position) {
        columnSelectIndex = position;
        for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
            View checkView = mRadioGroup_content.getChildAt(position);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k/2 - mScreenWidth/2;
            mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
        }

        for (int j = 0; j < mRadioGroup_content.getChildCount(); j++) {
            View checkView = mRadioGroup_content.getChildAt(j);
            boolean isCheck;
            if (j == position) {
                isCheck = true;
            } else {
                isCheck = false;
            }
            checkView.setSelected(isCheck);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
// TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(side_drawer.isMenuShowing() ||side_drawer.isSecondaryMenuShowing()){
                side_drawer.showContent();
            }else {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    Toast.makeText(this, "再按一次退出",
                            Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
            }
            return true;
        }
        //拦截MENU按钮点击事件，让他无任何操作
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
