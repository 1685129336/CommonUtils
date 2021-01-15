package core.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mvvmcore.R;

import core.api.ErrorView;

public class DefaultTitleView extends FrameLayout {
    private Context context;

    public DefaultTitleView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public DefaultTitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private TextView tvTitle;
    private TextView tvBack;
    private ImageView ivBack;
    public LinearLayout back;

    private void init(Context context) {
        this.context = context;
        View titleView = LayoutInflater.from(context).inflate(R.layout.title_layout, null);
        titleView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(titleView);
        tvBack = titleView.findViewById(R.id.tv_back);
        tvTitle = titleView.findViewById(R.id.tv_title);
        ivBack = titleView.findViewById(R.id.iv_back);
        back = titleView.findViewById(R.id.back);
    }

    public void setTitleText(String text) {
        tvTitle.setText(text);
    }

    public void setBackIcon(int res) {
        ivBack.setImageResource(res);
    }

    public void setBackText(String text) {
        tvBack.setText(text);
    }
}
