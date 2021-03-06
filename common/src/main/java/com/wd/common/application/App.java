package com.wd.common.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.LogUtils;

import java.util.Locale;

import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.onAdaptListener;

public class App extends Application {

    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationConText.context = getApplicationContext();
        /**
         * 屏幕适配
         */
        mContext =this;
        AutoSize.initCompatMultiProcess(this);

        AutoSizeConfig.getInstance().setExcludeFontScale(true).setOnAdaptListener(new onAdaptListener() {
            @Override
            public void onAdaptBefore(Object target, Activity activity) {
                //使用以下代码, 可支持 Android 的分屏或缩放模式, 但前提是在分屏或缩放模式下当用户改变您 App 的窗口大小时
                //系统会重绘当前的页面, 经测试在某些机型, 某些情况下系统不会重绘当前页面, ScreenUtils.getScreenSize(activity) 的参数一定要不要传 Application!!!
                //AutoSizeConfig.getInstance().setScreenWidth(ScreenUtils.getScreenSize(activity)[0]);
                // AutoSizeConfig.getInstance().setScreenHeight(ScreenUtils.getScreenSize(activity)[1]);
                LogUtils.d(String.format(Locale.ENGLISH, "%s onAdaptBefore!", target.getClass().getName()));
            }
            @Override
            public void onAdaptAfter(Object target, Activity activity) {
                LogUtils.d(String.format(Locale.ENGLISH, "%s onAdaptAfter!", target.getClass().getName()));
            }
        }).setBaseOnWidth(false).setUseDeviceSize(true);

    }
}
