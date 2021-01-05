package com.example.common.event.api;

import android.text.Editable;
import android.view.View;
import android.widget.CompoundButton;

public interface IEvent {
    interface OnClickListener {
        void onClick(View v);
    }
    interface OnCheckedChangeListener {
        void onCheckChange(CompoundButton buttonView, boolean isChecked);
    }
    abstract class OnTextChangedListener {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        public void afterTextChanged(Editable s) {}
    }
    abstract class OnPageChangeListener{

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

        public void onPageSelected(int position) {}

        public void onPageScrollStateChanged(int state) { }
    }
}
