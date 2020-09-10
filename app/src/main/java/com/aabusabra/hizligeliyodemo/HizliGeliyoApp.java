package com.aabusabra.hizligeliyodemo;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.aabusabra.hizligeliyodemo.helper.Constants;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class HizliGeliyoApp extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(Constants.FONT_Titillium_REG)
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
