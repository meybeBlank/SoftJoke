package com.fengz.softjoke.utils;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.fengz.softjoke.base.MyApplication;

import javax.inject.Inject;

public class ToastUtils {
    private static Toast mToast = null;

    public static void show( String msg) {
        showSingleToast( msg, Toast.LENGTH_SHORT);
    }

    /**
     * 单例toast 多个toast即时刷新
     */
    private static void showSingleToast(String msg, int duraion) {
        if (mToast == null) {
            mToast = Toast.makeText(MyApplication.getContext(), "", duraion);
        }
        mToast.setText(msg);
        mToast.setDuration(duraion);
        mToast.show();
    }
}
