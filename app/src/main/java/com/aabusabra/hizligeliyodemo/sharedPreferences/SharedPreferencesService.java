package com.aabusabra.hizligeliyodemo.sharedPreferences;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.aabusabra.hizligeliyodemo.comm.ProductResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class SharedPreferencesService {

    private static Gson GSON = new Gson();
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    public SharedPreferencesService(SharedPreferences preferences) {
        this.preferences = preferences;
        this.editor = preferences.edit();
    }


    public void clearAllSharedPreferences()
    {
        this.editor.clear();
        this.editor.commit();
    }



    public List<ProductResult> getAllProducts() {
        String json = preferences.getString(SharedPreferencesKeys.PRODUCTS, "");
        List<ProductResult> list = new ArrayList<>();
        if (!TextUtils.isEmpty(json)) {
            Type type = new TypeToken<List<ProductResult>>() {
            }.getType();
            list = GSON.fromJson(json, type);
            return list;
        } else {
            return new ArrayList<>();
        }
    }

    public void addProduct(ProductResult p) {
        List<ProductResult> products = getAllProducts();

        if (!products.contains(p))
            products.add(p);

        String json = GSON.toJson(products);
        editor.putString(SharedPreferencesKeys.PRODUCTS, json);
        editor.commit();
    }

    public void clearAllProducts()
    {
        editor.putString(SharedPreferencesKeys.PRODUCTS, "");
        editor.commit();
    }




}
