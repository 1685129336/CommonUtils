package core.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import core.api.LoadingView;

public abstract class BaseLoadingView extends FrameLayout implements LoadingView {
    public BaseLoadingView(@NonNull Context context) {
        super(context);
    }

    public BaseLoadingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseLoadingView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
