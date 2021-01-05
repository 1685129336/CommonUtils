package com.example.common.attribute.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class SlidingTabLayoutBean extends BaseObservable {
    private ViewPager vp;
    private List<String> titles;
    private FragmentActivity activity;
    private ArrayList<Fragment> fragments;
    private FragmentManager fragmentManager;

    public SlidingTabLayoutBean(ViewPager vp, List<String> titles, FragmentActivity activity, ArrayList<Fragment> fragments, FragmentManager fragmentManager) {
        this.vp = vp;
        this.titles = titles;
        this.activity = activity;
        this.fragments = fragments;
        this.fragmentManager = fragmentManager;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Bindable
    public ViewPager getVp() {
        return vp;
    }

    public void setVp(ViewPager vp) {
        this.vp = vp;
        notifyPropertyChanged(BR.vp);
    }
    @Bindable
    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
        notifyPropertyChanged(BR.titles);
    }
    @Bindable
    public FragmentActivity getActivity() {
        return activity;
    }

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
        notifyPropertyChanged(BR.activity);
    }
    @Bindable
    public ArrayList<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
        notifyPropertyChanged(BR.fragments);
    }
}
