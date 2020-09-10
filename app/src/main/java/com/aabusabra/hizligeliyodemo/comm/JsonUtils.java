package com.aabusabra.hizligeliyodemo.comm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;


public class JsonUtils {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private static Gson gson;

    public static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    //.setDateFormat("yyyy-MM-dd hh:mm:ss")
                    .create();
        }
        return gson;
    }
}
