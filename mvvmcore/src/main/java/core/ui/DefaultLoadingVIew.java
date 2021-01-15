package core.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mvvmcore.R;

import core.api.LoadingView;

public class DefaultLoadingVIew extends BaseLoadingView {
    private Context context;
    public DefaultLoadingVIew(@NonNull Context context) {
        super(context);
        init(context);
    }



    public DefaultLoadingVIew(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context) {
        this.context = context;
        View loadView = LayoutInflater.from(context).inflate(R.layout.load_default_layout, null);
        loadView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        addView(loadView);
    }
}
