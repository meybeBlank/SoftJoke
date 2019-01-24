package com.fengz.softjoke.base;

import android.app.Application;

import com.fengz.softjoke.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * 创建时间：2018/12/24
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：dagger-android 基础实现
 */
public class MyApplication extends DaggerApplication {

    private static Application mInstance;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static Application getContext() {
        if (mInstance == null) mInstance = new MyApplication();
        return mInstance;
    }
}
