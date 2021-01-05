package com.example.common.attribute.recyclerview;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter {
    @BindingAdapter(value = {"adapter", "layoutManager", "itemDecoration"}, requireAll = false)
    public static void setData(RecyclerView recyclerView, RecyclerView.Adapter adapter, RecyclerView.LayoutManager manager, RecyclerView.ItemDecoration decoration) {
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
        }
        if (manager != null) {
            recyclerView.setLayoutManager(manager);
        }
        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
    }
}
