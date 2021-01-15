package core.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mvvmcore.R;

import core.api.ErrorView;

public class DefaultErrorView extends BaseErrorView {
    private Context context;

    public DefaultErrorView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public DefaultErrorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private ImageView ivError;
    private TextView tvError;

    private void init(Context context) {
        this.context = context;
        View errorView = LayoutInflater.from(context).inflate(R.layout.error_default_layout, null);
        errorView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(errorView);

        ivError = errorView.findViewById(R.id.iv_error_image);
        tvError = errorView.findViewById(R.id.tv_error_msg);
    }

    @Override
    public void setNetState(NetState state) {
        switch (state) {
            case _404:
                ivError.setImageResource(R.drawable.ic_404);
                tvError.setText("页面找不到了");
                break;
            case _500:
                ivError.setImageResource(R.drawable.ic_500);
                tvError.setText("服务器异常 请稍后再试");
                break;
            case NO_DATA:
                ivError.setImageResource(R.drawable.ic_not_data);
                tvError.setText("此页面暂时没有数据");
                break;
            case NOT_NETWORK:
                ivError.setImageResource(R.drawable.ic_not_network);
                tvError.setText("网络连接超时，请查看手机网络连接");
                break;
        }
    }

    @Override
    public void setOnErrorClickListener(OnClickListener l) {
        ivError.setOnClickListener(l);
    }
}
