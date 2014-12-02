package com.example.CSDNCLient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by never on 2014/8/30.
 */
public class NewsFragmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private FragmentManager fragmentManager;

    public NewsFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentManager = fm;
    }

    public NewsFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragmentManager = fm;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        if (this.fragments != null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            for (Fragment fragment : this.fragments) {
                transaction.remove(fragment);
            }
            transaction.commit();
            transaction = null;
            fragmentManager.executePendingTransactions();
        }
        this.fragments = fragments;
        notifyDataSetChanged();
    }
}
