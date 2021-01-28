package com.tengshi.basemodule.widget.dialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 作者: adam
 * 日期: 1/26/21 - 11:35 AM
 * 邮箱: itgaojian@163.com
 * 描述:
 */
public class MDialogRootView extends FrameLayout {

    private int width;  //
    private int height; //
    private int maxWidth;  //最大宽度
    private int maxHeight; //最大高度

    private float left_top_radius; //
    private float right_top_radius; //
    private float right_bottom_radius; //
    private float left_bottom_radius; //

    private Path path = new Path(); //

    public MDialogRootView(@NonNull Context context) {
        this(context, null);
    }

    public MDialogRootView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MDialogRootView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (width == w && height == h) {
            return;
        }
        width = w;
        height = h;
        setBgRadius();
    }

    /**
     * 设置背景圆角
     *
     * @param bgRadius
     */
    public MDialogRootView setBgRadius(float bgRadius) {
        return setBgRadius(bgRadius, bgRadius, bgRadius, bgRadius);
    }

    public MDialogRootView setBgRadius(float left_top_radius, float right_top_radius, float right_bottom_radius, float left_bottom_radius) {
        this.left_top_radius = left_top_radius;
        this.right_top_radius = right_top_radius;
        this.right_bottom_radius = right_bottom_radius;
        this.left_bottom_radius = left_bottom_radius;
        setBgRadius();
        return this;
    }

    private MDialogRootView setBgRadius() {
        path.reset();
        path.addRoundRect(new RectF(0, 0, width, height), new float[]{
                        left_top_radius, left_top_radius,
                        right_top_radius, right_top_radius,
                        right_bottom_radius, right_bottom_radius,
                        left_bottom_radius, left_bottom_radius},
                Path.Direction.CW);
        invalidate();
        return this;
    }


    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
        invalidate();
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(getWidthMeasureSpec(widthMeasureSpec), getHeightMeasureSpec(heightMeasureSpec));
    }

    //最大宽高处理
    public int getWidthMeasureSpec(int widthMeasureSpec) {
        if (maxWidth > 0) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(maxWidth, MeasureSpec.AT_MOST);
        }
        return widthMeasureSpec;
    }

    public int getHeightMeasureSpec(int heightMeasureSpec) {
        if (maxHeight > 0) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST);
        }
        return heightMeasureSpec;
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.clipPath(path);
        super.draw(canvas);
    }


    /**
     * dp转px
     */
    private int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
    }
}
