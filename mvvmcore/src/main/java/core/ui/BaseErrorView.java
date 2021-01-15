package core.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import core.api.ErrorView;

public abstract class BaseErrorView extends FrameLayout implements ErrorView {
    public BaseErrorView(@NonNull Context context) {
        super(context);
    }

    public BaseErrorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseErrorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
