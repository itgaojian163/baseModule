package com.tengshi.basemodule.widget.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tengshi.basemodule.R;
import com.tengshi.basemodule.utils.ConvertUtils;
import com.tengshi.basemodule.utils.ScreenUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatDialog;

/**
 * 作者: adam
 * 日期: 1/26/21 - 11:02 AM
 * 邮箱: itgaojian@163.com
 * 描述: 公共Dialog
 */
public class MDialog extends AppCompatDialog {


    public static final int ANIM_TOP = 132;
    public static final int ANIM_BOTTOM = 133;
    public static final int ANIM_LEFT = 134;
    public static final int ANIM_RIGHT = 135;
    public static final int ANIM_SCALE = 136;
    public static final int ANIM_DEFAULT = 137;

    @IntDef({ANIM_DEFAULT, ANIM_BOTTOM, ANIM_TOP, ANIM_LEFT, ANIM_RIGHT, ANIM_SCALE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AnimType {
    }


    protected MDialogRootView controlView;
    protected Context mContext;
    protected int mLayoutId;
    private int width;
    private int height;
    private BgBean mBgBean = new BgBean();
    protected SparseArray<View> views = new SparseArray<>();
    protected List<Integer> cancelIds = new ArrayList<>();

    private MDialog(@NonNull Context context) {
        this(context, R.layout.layout_dialog_confirm);
    }

    private MDialog(@NonNull Context context, int layoutId) {
        this(context, layoutId, R.style.BaseDialog);
    }

    private MDialog(@NonNull Context context, int layoutId, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        this.mLayoutId = layoutId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controlView = new MDialogRootView(mContext);
        View view = LayoutInflater.from(mContext).inflate(mLayoutId, null);
        controlView.addView(view);
        setContentView(controlView);
        init();
    }


    protected void init() {
        setCanceledOnTouchOutside(true);
        getWindow().setBackgroundDrawable(getRoundRectDrawable(ConvertUtils.dp2px(0), Color.TRANSPARENT));
//        width = (int) (ScreenUtils.getScreenWidth() * 0.8);
        width = WindowManager.LayoutParams.WRAP_CONTENT;
        height = WindowManager.LayoutParams.WRAP_CONTENT;
        setWidthHeight();
        getWindow().setWindowAnimations(R.style.li_dialog_default);
    }

    public MDialog setTouchoutCancel(boolean isCancel) {
        this.setCanceledOnTouchOutside(isCancel);
        return this;
    }

    public MDialog setCancel(boolean isCancel) {
        this.setCancelable(isCancel);
        return this;
    }

    public static MDialog newInstance(@NonNull Context context) {
        return new MDialog(context).with();
    }

    public static MDialog newInstance(@NonNull Context context, int layoutId) {
        return new MDialog(context, layoutId).with();
    }

    public static MDialog newInstance(@NonNull Context context, int layoutId, int themeResId) {
        return new MDialog(context, layoutId, themeResId).with();
    }

    protected MDialog with() {
        show();
        dismiss();
        return this;
    }

    /**
     * 设置背景颜色
     *
     * @return
     */
    public MDialog setBgColor(@ColorInt int color) {
        mBgBean.color = color;
        return refreshBg();
    }

    public MDialog setBgColor(@Size(min = 1) String colorString) {
        mBgBean.color = Color.parseColor(colorString);
        return refreshBg();
    }

    /**
     * 渐变背景
     *
     * @param orientation
     * @param colors
     * @return
     */
    public MDialog setBgColor(GradientDrawable.Orientation orientation, @ColorInt int... colors) {
        mBgBean.gradientsOrientation = orientation;
        mBgBean.gradientsColors = colors;
        return refreshBg();
    }

    public MDialog setBgColor(GradientDrawable.Orientation orientation, @Size(min = 1) String... colorStrings) {
        mBgBean.gradientsOrientation = orientation;
        if (colorStrings == null) {
            return this;
        }
        mBgBean.gradientsColors = new int[colorStrings.length];
        for (int i = 0; i < mBgBean.gradientsColors.length; i++) {
            mBgBean.gradientsColors[i] = Color.parseColor(colorStrings[i]);
        }
        return refreshBg();
    }

    public MDialog setBgColorRes(@ColorRes int colorRes) {
        mBgBean.color = mContext.getResources().getColor(colorRes);
        return refreshBg();
    }

    public MDialog setBgColorRes(GradientDrawable.Orientation orientation, @Size(min = 1) String... colorRes) {
        mBgBean.gradientsOrientation = orientation;
        mBgBean.gradientsColors = new int[colorRes.length];
        for (int i = 0; i < colorRes.length; i++) {
            mBgBean.gradientsColors[i] = Color.parseColor(colorRes[i]);
        }
        return refreshBg();
    }

    public MDialog setBgColorRes(GradientDrawable.Orientation orientation, @ColorRes int... colorRes) {
        mBgBean.gradientsOrientation = orientation;
        mBgBean.gradientsColors = new int[colorRes.length];
        for (int i = 0; i < colorRes.length; i++) {
            mBgBean.gradientsColors[i] = getColor(colorRes[i]);
        }
        return refreshBg();
    }

    /**
     * 设置位置
     *
     * @param gravity
     * @param offX
     * @param offY
     */
    public MDialog setGravity(int gravity, int offX, int offY) {
        setGravity(gravity);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.x = offX;
        layoutParams.y = offY;
        getWindow().setAttributes(layoutParams);
        return this;
    }

    public MDialog setGravity(int gravity) {
        getWindow().setGravity(gravity);
        return this;
    }

    /**
     * 刷新背景
     *
     * @return
     */
    protected MDialog refreshBg() {
        controlView.setBackground(mBgBean.getRoundRectDrawable());
        controlView.setBgRadius(
                mBgBean.left_top_radius,
                mBgBean.right_top_radius,
                mBgBean.right_bottom_radius,
                mBgBean.left_bottom_radius);
        return this;
    }

    /**
     * 设置背景圆角
     *
     * @param bgRadius
     */
    public MDialog setBgRadius(float bgRadius) {

        setBgRadius(bgRadius, bgRadius, bgRadius, bgRadius);
        return refreshBg();
    }

    public MDialog setBgRadius(float left_top_radius, float right_top_radius, float right_bottom_radius, float left_bottom_radius) {
        mBgBean.left_top_radius = ConvertUtils.dp2px(mContext, left_top_radius);
        mBgBean.right_top_radius = ConvertUtils.dp2px(mContext, right_top_radius);
        mBgBean.right_bottom_radius = ConvertUtils.dp2px(mContext, right_bottom_radius);
        mBgBean.left_bottom_radius = ConvertUtils.dp2px(mContext, left_bottom_radius);
        return refreshBg();
    }

    /**
     * 设置背景圆角
     *
     * @param bgRadius
     */
    public MDialog setBgRadiusPX(int bgRadius) {
        setBgRadiusPX(bgRadius, bgRadius, bgRadius, bgRadius);
        return refreshBg();
    }

    public MDialog setBgRadiusPX(float left_top_radius, float right_top_radius, float right_bottom_radius, float left_bottom_radius) {
        mBgBean.left_top_radius = left_top_radius;
        mBgBean.right_top_radius = right_top_radius;
        mBgBean.right_bottom_radius = right_bottom_radius;
        mBgBean.left_bottom_radius = left_bottom_radius;
        return refreshBg();
    }

    /**
     * 内置动画
     *
     * @param styleType 类型
     * @return
     */
    public MDialog setAnimations(@AnimType int styleType) {
        int style = -1;
        switch (styleType) {
            case ANIM_DEFAULT: //默认
                style = R.style.li_dialog_default;
                break;
            case ANIM_SCALE:
                style = R.style.li_dialog_scale;
                break;
            case ANIM_LEFT:
                style = R.style.li_dialog_translate_left;
                break;
            case ANIM_TOP:
                style = R.style.li_dialog_translate_top;
                break;
            case ANIM_RIGHT:
                style = R.style.li_dialog_translate_right;
                break;
            case ANIM_BOTTOM:
                style = R.style.li_dialog_translate_bottom;
                break;
        }
        if (style != -1) {
            setAnimationsStyle(style);
        }
        return this;
    }

    /**
     * 设置宽高(精确)
     */
    private MDialog setWidthHeight() {
        ViewGroup.LayoutParams layoutParams = controlView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        controlView.setLayoutParams(layoutParams);
        return this;
    }

    /**
     * 自定义动画
     *
     * @param style
     * @return
     */
    public MDialog setAnimationsStyle(int style) {
        getWindow().setWindowAnimations(style);
        return this;
    }

    /**
     * 设置宽占屏幕的比例
     *
     * @param widthRatio
     */
    public MDialog setWidthRatio(double widthRatio) {
        width = (int) (ScreenUtils.getScreenWidth(mContext) * widthRatio);
        setWidthHeight();
        return this;
    }

    public MDialog setMaxWidthRatio(double widthRatio) {
        return setMaxWidthPX((int) (ScreenUtils.getScreenWidth(mContext) * widthRatio));
    }

    public MDialog setMinWidthRatio(double widthRatio) {
        return setMinWidthPX((int) (ScreenUtils.getScreenWidth(mContext) * widthRatio));
    }

    /**
     * 遮罩透明度
     *
     * @param value 0-1f
     * @return
     */
    public MDialog setMaskValue(float value) {
        getWindow().setDimAmount(value);
        return this;
    }

    /**
     * 最小宽
     *
     * @param width
     * @return
     */
    public MDialog setMinWidth(int width) {
        setMinWidthPX(ConvertUtils.dp2px(mContext, width));
        return this;
    }

    public MDialog setMinWidthPX(int width) {
        controlView.setMinimumWidth(width);
        return this;
    }

    /**
     * 设置高占屏幕的比例
     *
     * @param heightRatio
     */
    public MDialog setHeightRatio(double heightRatio) {
        height = (int) (ScreenUtils.getScreenHeight() * heightRatio);
        setWidthHeight();
        return this;
    }

    public MDialog setMaxHeightRatio(double heightRatio) {
        return setMaxHeightPX((int) (ScreenUtils.getScreenWidth(mContext) * heightRatio));
    }

    public MDialog setMinHeightRatio(double heightRatio) {
        return setMinHeightPX((int) (ScreenUtils.getScreenWidth(mContext) * heightRatio));
    }

    /**
     * 最大宽
     *
     * @param width
     * @return
     */
    public MDialog setMaxWidth(int width) {
        setMaxWidthPX(ConvertUtils.dp2px(mContext, width));
        return this;
    }

    public MDialog setMaxWidthPX(int width) {
        controlView.setMaxWidth(width);
        return this;
    }

    /**
     * 最大高度
     *
     * @param height
     * @return
     */
    public MDialog setMaxHeight(int height) {
        setMaxHeightPX(ConvertUtils.dp2px(mContext, height));
        return this;
    }

    public MDialog setMaxHeightPX(int height) {
        controlView.setMaxHeight(height);
        return this;
    }

    /**
     * 最小高度
     *
     * @param height
     * @return
     */
    public MDialog setMinHeight(int height) {
        setMinHeightPX(ConvertUtils.dp2px(mContext, height));
        return this;
    }

    public MDialog setMinHeightPX(int height) {
        controlView.setMinimumHeight(height);
        return this;
    }

    public int getColor(int colorResId) {
        return mContext.getResources().getColor(colorResId);
    }

    public static GradientDrawable getRoundRectDrawable(int radius, int color) {
        float[] radiuss = {radius, radius, radius, radius, radius, radius, radius, radius};
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadii(radiuss);
        drawable.setColor(color != 0 ? color : Color.TRANSPARENT);
        return drawable;
    }

    /**
     * 设置监听
     *
     * @param onClickListener
     * @param viewIds
     */
    public MDialog setOnClickListener(final DialogOnClickListener onClickListener, int... viewIds) {
        MDialog dialog = this;
        for (int i = 0; i < viewIds.length; i++) {
            if (cancelIds.contains(viewIds[i])) {
                getView(viewIds[i]).setOnClickListener(v -> {
                    onClickListener.onClick(v, dialog);
                    dialog.dismiss();
                });
            } else {
                getView(viewIds[i]).setOnClickListener(v -> onClickListener.onClick(v, dialog));
            }
        }
        return this;
    }

    /**
     * 设置 关闭dialog的按钮
     *
     * @param viewIds
     * @return
     */
    public MDialog setCancelBtn(int... viewIds) {
        for (int i = 0; i < viewIds.length; i++) {
            if (cancelIds.contains(viewIds[i])) {
                continue;
            }
            cancelIds.add(viewIds[i]);
            getView(viewIds[i]).setOnClickListener(v -> dismiss());
        }
        return this;
    }

    /**
     * 设置textview的文字
     */
    public MDialog setText(@IdRes int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    public MDialog setText(@IdRes int viewId, @StringRes int strId) {
        TextView view = getView(viewId);
        view.setText(strId);
        return this;
    }

    /**
     * 获取edittext文字
     */
    public String getText(@IdRes int viewId) {
        EditText etView = getView(viewId);
        return etView.getText().toString().trim();
    }

    /**
     * 设置文字大小
     */
    public MDialog setTextSize(@IdRes int viewId, float size) {
        setTextSizePX(viewId, ConvertUtils.dp2px(mContext, size));
        return this;
    }

    public MDialog setTextSizePX(@IdRes int viewId, float size) {
        TextView view = getView(viewId);
        view.setTextSize(ConvertUtils.sp2px(mContext, size));
        return this;
    }

    public MDialog setImageResource(@IdRes int viewId, @DrawableRes int imageResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public MDialog setTextColor(@IdRes int viewId, @ColorInt int color) {
        TextView tvView = getView(viewId);
        tvView.setTextColor(color);
        return this;
    }

    public static MDialog showLoading(Context ctx, String hintText) {
        return MDialog.newInstance(ctx, R.layout.layout_custom_loading_dialog)
                .setAnimations(MDialog.ANIM_SCALE)
                .setTextColor(R.id.tv_load_dialog, Color.RED)
                .setText(R.id.tv_load_dialog, hintText)
                .setBgRadius(5f)
                .setBgColor(Color.parseColor("#88000000"))
                .setCancel(false)
                .setTouchoutCancel(false)
                .setWidthRatio(0.4)
                .setMaskValue(0.2f);
    }

    public interface DialogOnClickListener {
        void onClick(View v, MDialog dialog);
    }
}
