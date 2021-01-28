package com.tengshi.basemodule.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * 提示工具类
 * Created by Xuer on 2017/3/9.
 */
public class UIUtil {
    /**
     * 显示短toast
     */
    public static void sToast(final Context context, final String msg) {
        new Handler(context.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 显示长toast
     */
    public static void lToast(final Context ctx, final String msg) {
        new Handler(ctx.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 圆形进度条dialog
     *
     * @param context
     * @param tips
     * @return
     */
    public static ProgressDialog initDialog(Context context, String tips) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(tips);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }


}
