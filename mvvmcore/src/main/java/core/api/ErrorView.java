package core.api;

import android.view.View;

public interface ErrorView {
    void setNetState(NetState state);
    void setOnErrorClickListener(View.OnClickListener l);

    public static enum NetState{
        _404, //404页面找不到
        _500,//500服务器异常
        NOT_NETWORK,//没有网络
        NO_DATA//页面没有数据
    }
}
