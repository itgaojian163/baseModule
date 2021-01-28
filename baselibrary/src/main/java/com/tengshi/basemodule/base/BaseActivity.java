package com.tengshi.basemodule.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tengshi.basemodule.R;
import com.tengshi.basemodule.utils.ToastUtils;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/**
 * 作者 : Adam on 2018/10/15.
 * 邮箱 : itgaojian@163.com
 * 描述 : 所有Activity的基类
 */
public abstract class BaseActivity extends FragmentActivity {
    public static final int STATE_LOAD_INIT = 13;
    public static final int STATE_LOAD_LOADING = 14;//加载中
    public static final int STATE_LOAD_SUCCESS = 15;//加载成功
    public static final int STATE_LOAD_EMPTY = 16;//加载失败
    public static final int STATE_LOAD_ERROR = 17;//加载失败
    public static final int STATE_LOAD_NET = 18;//网络连接失败
    protected View mContentView;                 //每个activity的内容区域
    public FrameLayout flBaseActivityContent;     //内容区域
    public Activity mActivity;                    //

    protected int mWidth = 0;
    protected Button mIbBack;
    protected TextView mTvBaseTitle;//标题
    protected ProgressBar mPbLoading;
    protected ImageView mIvEmptyData;
    protected RelativeLayout mRlTitleBar;
    protected TextView mTvErrorHint;
    protected RelativeLayout mRlNotify;
    protected ImageView mIvNotify;
    protected TextView mTvNotify;
    protected ImageView mIvAppBack;
    protected TextView mTvAppTitleFunction;//titleBar功能图片
    protected ImageView mIvAppTitleFunction;//titleBar功能文字
    private LinearLayout mLlSearchBar;
    private ImageView mIvAppSearchBack;
    private TextView mTvAppSearchTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mWidth = metrics.widthPixels;
        mActivity = this;
        baseActivityInitViews();//初始化视图
        isMainActivity(false);
        setTouchDelegate(mIvAppBack, 30);
        mIvAppBack.setOnClickListener(v -> {
            hideSoftKeyboard();
            finish();
        });
        mContentView = LayoutInflater.from(this).inflate(setLayoutId(), null);
        flBaseActivityContent.addView(mContentView);
        mContentView.setOnTouchListener((v, event) -> {
            hideSoftKeyboard();
            return false;
        });
        refreshView(STATE_LOAD_LOADING);
        initData();
        initMapView(savedInstanceState);//初始化MapView
        mRlNotify.setVisibility(View.GONE);

    }

    protected void isMainActivity(boolean isMain) {
        if (isMain) {
            mIvAppBack.setVisibility(View.GONE);
        } else {
            mIvAppBack.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 初始化数据
     */
    public void initData() {

    }

    /**
     * 刷新页面
     *
     * @param state 页面状态码
     */
    public void refreshView(int state) {
        switch (state) {
            case STATE_LOAD_LOADING://加载中
                mPbLoading.setVisibility(View.VISIBLE);
                mIvEmptyData.setVisibility(View.GONE);
                mTvErrorHint.setVisibility(View.VISIBLE);
                mTvErrorHint.setText(getResources().getString(R.string.loading));
                flBaseActivityContent.setVisibility(View.INVISIBLE);
                break;
            case STATE_LOAD_SUCCESS://加载成功
                mPbLoading.setVisibility(View.GONE);
                mIvEmptyData.setVisibility(View.GONE);
                mTvErrorHint.setVisibility(View.GONE);
                flBaseActivityContent.setVisibility(View.VISIBLE);
                break;
            case STATE_LOAD_EMPTY://空数据
                mPbLoading.setVisibility(View.GONE);
                mIvEmptyData.setVisibility(View.VISIBLE);
                mTvErrorHint.setVisibility(View.VISIBLE);
                mTvErrorHint.setText(getResources().getString(R.string.empty_data));
                flBaseActivityContent.setVisibility(View.INVISIBLE);
                break;
            case STATE_LOAD_ERROR://加载失败
                mPbLoading.setVisibility(View.GONE);
                mIvEmptyData.setVisibility(View.VISIBLE);
                mTvErrorHint.setVisibility(View.VISIBLE);
                mTvErrorHint.setText(getResources().getString(R.string.loading_error));
                flBaseActivityContent.setVisibility(View.INVISIBLE);
                break;
            case STATE_LOAD_NET://网络连接失败
                mPbLoading.setVisibility(View.GONE);
                mIvEmptyData.setVisibility(View.VISIBLE);
                mTvErrorHint.setVisibility(View.VISIBLE);
                mTvErrorHint.setText(getResources().getString(R.string.loading_net));
                flBaseActivityContent.setVisibility(View.INVISIBLE);
                break;
        }
    }

    /**
     * 重新启动App
     */
    public void reStartApp() {
        ToastUtils.showShort("请重新登录.");
        Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    /**
     * 初始化标题布局
     */
    private void baseActivityInitViews() {
        mRlTitleBar = findViewById(R.id.rl_base_app_title_back);//文字标题titlebar
        mLlSearchBar = findViewById(R.id.ll_app_search_title_bar); //搜索titlebar
        mRlTitleBar.setVisibility(View.VISIBLE);
        mLlSearchBar.setVisibility(View.GONE);
        mIvAppSearchBack = findViewById(R.id.iv_app_search_back);
        mTvAppSearchTitle = findViewById(R.id.tv_app_search_title);
        mIvAppBack = findViewById(R.id.iv_app_back);
        mIvAppTitleFunction = findViewById(R.id.iv_app_title_function);
        flBaseActivityContent = findViewById(R.id.fl_app_content);
        mTvBaseTitle = findViewById(R.id.tv_app_title_txt);
        mTvAppTitleFunction = findViewById(R.id.tv_app_title_function);
        mPbLoading = findViewById(R.id.pb_app_loading);
        mIvEmptyData = findViewById(R.id.iv_app_empty_data);
        mTvErrorHint = findViewById(R.id.tv_app_error_hint);
        mRlNotify = findViewById(R.id.rl_notify);
        mIvNotify = findViewById(R.id.iv_nootify_point);
        mTvNotify = findViewById(R.id.tv_notify);
        mIvAppSearchBack.setOnClickListener(v -> {
            hideSoftKeyboard();
            finish();
        });
    }


    private void openActivity(String path) {
//        ARouter.getInstance()
//                .build(path)
//                .navigation();
    }

    private String getRunningTopActivity() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity = manager.getRunningTasks(1).get(0).topActivity.getClassName();
        return runningActivity;
    }

    /**
     * 显示toast
     *
     * @param content 要显示的内容
     */
    public void showToast(String content) {
        ToastUtils.showShort(content);
    }


    /**
     * 隐藏软键盘
     */
    public void hideSoftKeyboard() {
        hideSoftKeyboard(getCurrentFocus());
    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showSoftKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null) {
            imm.showSoftInput(view, 0);
        }
    }

    /**
     * 初始化高德地图
     * 使用Butterknife注意
     *
     * @param bundle
     */
    protected void initMapView(Bundle bundle) {

    }

    /**
     * 初始化视图
     */
    protected abstract int setLayoutId();

    /**
     * 程序字号不随系统设置而改变
     *
     * @return
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    public void onBackPressed() {
//        finish();
    }

    /**
     * 无参数跳转
     */
    protected void openArouterActivity(String path) {
        ARouter.getInstance().build(path).navigation();
    }

    /**
     * 浏览图片
     * 需要引入Modulemedia模块
     *
     * @param imageUrls 图片地址Arraylist
     */
    protected void startImageActivity(ArrayList imageUrls) {
        ARouter.getInstance()
                .build("")
                .withStringArrayList("imgUrls", imageUrls)
                .navigation();
    }

    /**
     * 打开拍摄 实现onActivityResult进行处理
     * 需要引入ModuleMedia模块
     * picUrl 返回的拍摄的路径key
     * movUrl 返回的录像的路径key
     * 123    相片响应码
     * 122    录像响应码
     *
     * @param isMovie     是否可以拍摄短视频
     * @param requestCode 请求码
     */
    protected void startCameraActivity(boolean isMovie, int requestCode) {
//        ARouter.getInstance()
//                .build(PathConfig.PATH_MODULEMEDIA_CAMERA)
//                .withBoolean("isMovie", isMovie)
//                .navigation(this, requestCode);
    }

    /**
     * 将view的点击区域放大
     *
     * @param view             需要放大的view
     * @param expandTouchWidth 放大的宽度
     */
    public void setTouchDelegate(final View view, final int expandTouchWidth) {
        final View parentView = (View) view.getParent();
        parentView.post(() -> {
            final Rect rect = new Rect();
            view.getHitRect(rect);
            rect.top -= expandTouchWidth;
            rect.bottom += expandTouchWidth;
            rect.left -= expandTouchWidth;
            rect.right += expandTouchWidth;
            TouchDelegate touchDelegate = new TouchDelegate(rect, view);
            parentView.setTouchDelegate(touchDelegate);
        });
    }
}
