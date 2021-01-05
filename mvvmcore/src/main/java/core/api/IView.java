package core.api;

import android.os.Bundle;
import android.view.View;

public interface IView {
    int layoutID();
    void initView();
    void initData();
    void toast(String msg, View view);
    void toast(String msg);
    void startActivity(Class<?> activity);
    void startActivity(Class<?> activity, Bundle data, String paramName);
}
