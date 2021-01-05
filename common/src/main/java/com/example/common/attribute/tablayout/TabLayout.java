package com.example.common.attribute.tablayout;

import android.util.Log;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.common.attribute.adapter.MyFragmentAdapter;
import com.example.common.attribute.bean.SlidingTabLayoutBean;
import com.flyco.tablayout.SlidingTabLayout;



public class TabLayout {
    @BindingAdapter({"tab_data"})
    public static void setData(SlidingTabLayout tabLayout, SlidingTabLayoutBean bean) {

        if(bean!=null){
            MyFragmentAdapter adapter = new MyFragmentAdapter(bean.getFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, bean.getFragments());
            bean.getVp().setAdapter(adapter);
            String[] strings = bean.getTitles().toArray(new String[bean.getTitles().size()]);
            tabLayout.setViewPager(bean.getVp(), strings);
        }
    }
}
