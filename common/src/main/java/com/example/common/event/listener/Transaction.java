package com.example.common.event.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.TimeUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.common.event.api.IEvent;
import com.example.common.event.api.IEvent.OnClickListener;

import java.util.concurrent.TimeUnit;


public class Transaction {
    /**
     * 控件点击事件(添加了按钮触发事件逻辑)
     * @param view
     * @param listener
     */
    @BindingAdapter({"my_onClick"})
    public static void onClick(View view, OnClickListener listener) {
        view.setOnClickListener((v) -> {
            listener.onClick(v);
        });
    }

    /**
     * checkbox状态改变事件
     * @param buttonView
     * @param listener
     */
    @BindingAdapter({"my_onCheckedChange"})
    public static void onCheckedChange(CompoundButton buttonView, IEvent.OnCheckedChangeListener listener) {
        buttonView.setOnCheckedChangeListener((btv, c) -> {
            listener.onCheckChange(btv, c);
        });
    }

    /**
     * editText内容改变事件
     * @param et
     * @param listener
     */
    @BindingAdapter({"my_onTextChange"})
    public static void onTextChange(EditText et, IEvent.OnTextChangedListener listener) {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                listener.beforeTextChanged(s, start, count, after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listener.onTextChanged(s, start, before, count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                listener.afterTextChanged(s);
            }
        });
    }

    /**
     * viewPager页面切换监听
     * @param vp
     * @param listener
     */
    @BindingAdapter({"my_onPageChange"})
    public static void onPageChange(ViewPager vp, IEvent.OnPageChangeListener listener){
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                listener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                listener.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                listener.onPageScrollStateChanged(state);
            }
        });
    }

}
