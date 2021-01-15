package core.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mvvmcore.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import core.api.ErrorView;
import core.api.LoadingView;

public class BaseLayout extends LinearLayout {
    private Context context;
    private DefaultTitleView titleView; //标题控件
    private BaseErrorView errorView; //错误页面
    private BaseLoadingView loadingView; //正在加载页面
    private View normalView; //正常页面，用户交互
    private OnClickListener errorClick;
    private Map<PageState, View> viewMap = new HashMap<PageState, View>();
    private static final String TAG = "-->BaseLayout:";
    public BaseLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public BaseLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.BaseLayout);
        showTitleView(attributes.getBoolean(R.styleable.BaseLayout_showTitle,true));
        setTitleText(attributes.getString(R.styleable.BaseLayout_titleText));
        setBackIcon(attributes.getResourceId(R.styleable.BaseLayout_title_back_icon,R.drawable.ic_baseline_arrow_back_ios_24));
        setBackText(attributes.getString(R.styleable.BaseLayout_title_back_text));
        setTitleHeight(attributes.getDimension(R.styleable.BaseLayout_titleHeight,120));
    }
    //设置标题高度
    private void setTitleHeight(float dimension) {
        ViewGroup.LayoutParams layoutParams = titleView.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = (int) dimension;
        titleView.setLayoutParams(layoutParams);
    }

    private void init(Context context) {
        this.context = context;
        setOrientation(VERTICAL);
        titleView = new DefaultTitleView(context);
        titleView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            titleView.setElevation(50);
        }
        addView(titleView);
    }
    //页面切换状态
    public void pageChangeState(PageState state) {
        hideView();
        View view = viewMap.get(state);
        if (view == null) {
            switch (state) {
                case ERROR:
                    View defaultErrorView = createDefaultErrorView();
                    defaultErrorView.setVisibility(VISIBLE);
                    break;
                case LOADING:
                    View defaultLoadingView = createDefaultLoadingView();
                    defaultLoadingView.setVisibility(VISIBLE);
                    break;
                case NORMAL:
                    Log.i(TAG, "神奇 normal view 尽然是null！！");
                    break;
            }
        } else {
            view.setVisibility(VISIBLE);
        }
    }
    //页面加载中状态
    public void pageErrorState(ErrorView.NetState state){
        pageChangeState(PageState.ERROR);
        errorView.setNetState(state);
        if(errorClick!=null){
            errorView.setOnErrorClickListener(errorClick);
        }
    }
    //页面错误状态
    public void pageLoadingState(){
        pageChangeState(PageState.LOADING);
    }
    //页面正常状态
    public void pageNormalState(){
        pageChangeState(PageState.NORMAL);
    }


    public void setErrorView(BaseErrorView errorView) {
        viewMap.put(PageState.ERROR,errorView);
        this.errorView = errorView;
        initPage(errorView);
    }

    public void setLoadingView(BaseLoadingView loadingView) {
        viewMap.put(PageState.LOADING,loadingView);
        this.loadingView = loadingView;
        initPage(loadingView);
    }

    private void initPage(ViewGroup viewGroup){
        removeView(viewGroup);
        viewGroup.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewGroup.setVisibility(GONE);
        addView(viewGroup);
    }

    public enum PageState {
        ERROR, LOADING, NORMAL
    }

    //显示隐藏标题控件
    public void showTitleView(Boolean t){
        if (t){
            titleView.setVisibility(VISIBLE);
        }else{
            titleView.setVisibility(GONE);
        }
    }
    //设置标题文字
    public void setTitleText(String text){
        titleView.setTitleText(text);
    }
    //设置返回键的图标
    public void setBackIcon(int res){
        titleView.setBackIcon(res);
    }
    //设置返回见文本
    public void setBackText(String text){
        titleView.setBackText(text);
    }
    public void setOnBackClickListener(OnClickListener o){
        titleView.back.setOnClickListener(o);
    }
    public void setOnErrorClickListener(OnClickListener o){
        errorClick = o;
    }

    private void hideView() {
        Set<Map.Entry<PageState, View>> entries = viewMap.entrySet();
        for (Map.Entry<PageState, View> entry : entries) {
            entry.getValue().setVisibility(GONE);
        }
    }

    private View createDefaultErrorView() {
        errorView = new DefaultErrorView(context);
        errorView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(errorView);
        viewMap.put(PageState.ERROR, errorView);
        return errorView;
    }

    private View createDefaultLoadingView() {
        loadingView = new DefaultLoadingVIew(context);
        loadingView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(loadingView);
        viewMap.put(PageState.LOADING, loadingView);
        return loadingView;
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt != null) {
                String tag = (String) childAt.getTag();
                if (tag != null && tag.equals("user")) {
                    viewMap.put(PageState.NORMAL, childAt);
                    normalView = childAt;
                }
            }
        }
    }

}
