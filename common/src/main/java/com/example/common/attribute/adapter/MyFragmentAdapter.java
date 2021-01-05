package com.example.common.attribute.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class MyFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;

    public MyFragmentAdapter(@NonNull FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public MyFragmentAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> fragments) {
        super(fm, behavior);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
