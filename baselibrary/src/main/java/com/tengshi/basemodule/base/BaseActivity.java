package com.tengshi.basemodule.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.TouchDelegate;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tengshi.basemodule.R;
import com.tengshi.basemodule.databinding.ActivityBaseBinding;
import com.tengshi.basemodule.utils.NetworkUtils;
import com.tengshi.basemodule.utils.ToastUtils;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewbinding.ViewBinding;

/**
 * 作者 : Adam on 2018/10/15.
 * 邮箱 : itgaojian@163.com
 * 描述 : 所有Activity的基类
 */
public abstract class BaseActivity<VB extends ViewBinding> extends FragmentActivity {
    public static final int STATE_LOAD_INIT = 13;
    public static final int STATE_LOAD_LOADING = 14;//加载中
    public static final int STATE_LOAD_SUCCESS = 15;//加载成功
    public static final int STATE_LOAD_EMPTY = 16;//加载失败
    public static final int STATE_LOAD_ERROR = 17;//加载失败
    public static final int STATE_LOAD_NET = 18;//网络连接失败
    protected VB mContentBinding;

    public Activity mActivity;                    //

    protected int mWidth = 0;
    private NetworkChangedReceiver mNetworkChangedReceiver;
    protected ActivityBaseBinding mBaseBinding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseBinding = ActivityBaseBinding.inflate(getLayoutInflater());
        setContentView(mBaseBinding.getRoot());
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mWidth = metrics.widthPixels;
        mActivity = this;
        baseActivityInitViews();//初始化视图
        isMainActivity(false);
        setTouchDelegate(mBaseBinding.ivAppBack, 30);
        mBaseBinding.ivAppBack.setOnClickListener(v -> {
            hideSoftKeyboard();
            finish();
        });
        mContentBinding = setLayoutId();
        mBaseBinding.flAppContent.addView(mContentBinding.getRoot());
        mContentBinding.getRoot().setOnTouchListener((v, event) -> {
            hideSoftKeyboard();
            return false;
        });
        refreshView(STATE_LOAD_LOADING);
        initData();
        initMapView(savedInstanceState);//初始化MapView
        mBaseBinding.rlNotify.setVisibility(View.GONE);
        registerNetState();
        mBaseBinding.ivAppEmptyData.setOnClickListener(v -> reLoadData());

    }

    protected void isMainActivity(boolean isMain) {
        if (isMain) {
            mBaseBinding.ivAppBack.setVisibility(View.GONE);
        } else {
            mBaseBinding.ivAppBack.setVisibility(View.VISIBLE);
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
                mBaseBinding.pbAppLoading.setVisibility(View.VISIBLE);
                mBaseBinding.tvAppErrorHint.setVisibility(View.GONE);
                mBaseBinding.tvAppErrorHint.setVisibility(View.VISIBLE);
                mBaseBinding.tvAppErrorHint.setText(getResources().getString(R.string.loading));
                mBaseBinding.flAppContent.setVisibility(View.INVISIBLE);
                break;
            case STATE_LOAD_SUCCESS://加载成功
                mBaseBinding.pbAppLoading.setVisibility(View.GONE);
                mBaseBinding.ivAppEmptyData.setVisibility(View.GONE);
                mBaseBinding.tvAppErrorHint.setVisibility(View.GONE);
                mBaseBinding.flAppContent.setVisibility(View.VISIBLE);
                break;
            case STATE_LOAD_EMPTY://空数据
                mBaseBinding.pbAppLoading.setVisibility(View.GONE);
                mBaseBinding.ivAppEmptyData.setVisibility(View.VISIBLE);
                mBaseBinding.ivAppEmptyData.setImageResource(R.drawable.ic_empty_data);
                mBaseBinding.tvAppErrorHint.setVisibility(View.VISIBLE);
                mBaseBinding.tvAppErrorHint.setText(getResources().getString(R.string.empty_data));
                mBaseBinding.flAppContent.setVisibility(View.INVISIBLE);
                break;
            case STATE_LOAD_ERROR://加载失败
                mBaseBinding.pbAppLoading.setVisibility(View.GONE);
                mBaseBinding.ivAppEmptyData.setVisibility(View.VISIBLE);
                mBaseBinding.tvAppErrorHint.setVisibility(View.VISIBLE);
                mBaseBinding.ivAppEmptyData.setImageResource(R.drawable.ic_data_error);
                mBaseBinding.tvAppErrorHint.setText(getResources().getString(R.string.loading_error));
                mBaseBinding.flAppContent.setVisibility(View.INVISIBLE);
                break;
            case STATE_LOAD_NET://网络连接失败
                mBaseBinding.pbAppLoading.setVisibility(View.GONE);
                mBaseBinding.ivAppEmptyData.setVisibility(View.VISIBLE);
                mBaseBinding.ivAppEmptyData.setImageResource(R.drawable.ic_net_error);
                mBaseBinding.tvAppErrorHint.setVisibility(View.VISIBLE);
                mBaseBinding.tvAppErrorHint.setText(getResources().getString(R.string.loading_net));
                mBaseBinding.flAppContent.setVisibility(View.INVISIBLE);
                break;
        }
    }

    /**
     * 重新加载数据
     * -网络错误
     * -空数据
     * -加载错误
     */
    protected abstract void reLoadData();

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
//        mRlTitleBar = findViewById(R.id.rl_base_app_title_back);//文字标题titlebar
//        mLlSearchBar = findViewById(R.id.ll_app_search_title_bar); //搜索titlebar
//        mRlTitleBar.setVisibility(View.VISIBLE);
//        mLlSearchBar.setVisibility(View.GONE);
//        mIvAppSearchBack = findViewById(R.id.iv_app_search_back);
//        mTvAppSearchTitle = findViewById(R.id.tv_app_search_title);
//        mIvAppBack = findViewById(R.id.iv_app_back);
//        mIvAppTitleFunction = findViewById(R.id.iv_app_title_function);
//        flBaseActivityContent = findViewById(R.id.fl_app_content);
//        mTvBaseTitle = findViewById(R.id.tv_app_title_txt);
//        mTvAppTitleFunction = findViewById(R.id.tv_app_title_function);
//        mPbLoading = findViewById(R.id.pb_app_loading);
//        mIvEmptyData = findViewById(R.id.iv_app_empty_data);
//        mTvErrorHint = findViewById(R.id.tv_app_error_hint);
//        mRlNotify = findViewById(R.id.rl_notify);
//        mIvNotify = findViewById(R.id.iv_nootify_point);
//        mTvNotify = findViewById(R.id.tv_notify);
        mBaseBinding.ivAppSearchBack.setOnClickListener(v -> {
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
    protected abstract VB setLayoutId();

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
     * 注册网络状态改变广播
     */
    protected void registerNetState() {
        mNetworkChangedReceiver = new NetworkChangedReceiver();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetworkChangedReceiver, intentFilter);
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


    private class NetworkChangedReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean connected = NetworkUtils.isConnected();
            if (!connected) {
                refreshView(STATE_LOAD_NET);
            }
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mNetworkChangedReceiver);
        super.onDestroy();
    }
}
