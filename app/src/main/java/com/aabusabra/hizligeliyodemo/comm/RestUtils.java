package com.aabusabra.hizligeliyodemo.comm;


import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class RestUtils {

    public static final String URL_BASE = "https://fakestoreapi.com";

    public static final String URL_GET_IMAGE = URL_BASE +"/img/";

    //Products
    public static final String URL_GET_PRODUCTS = URL_BASE + "/products";




    public static final String TAG;
    private static final OkHttpClient client;

    static {
        TAG = RestUtils.class.getSimpleName();
        client = new OkHttpClient.Builder()
                .readTimeout(7, TimeUnit.MINUTES)
                .writeTimeout(7, TimeUnit.MINUTES)
                .build();
    }

    public static String postJson(String url, Map<String, Object> map) throws IOException {
        OkHttpClient mClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .build();
        //MultipartBody.Builder builder = new MultipartBody.Builder();
        // builder.setType(MultipartBody.FORM);
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> e : map.entrySet()) {
            //builder.addFormDataPart(e.getKey(), e.getValue().toString());
            builder.add(e.getKey(), e.getValue().toString());
        }

        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        okhttp3.Response response = mClient.newCall(request).execute();
        if (!response.isSuccessful())
            for (int i = 0; i < 3; i++) {
                response = mClient.newCall(request).execute();
                if (response.isSuccessful())
                    break;
            }
        return response.body().string();
    }

    public static String postJson(String url, Map<String, Object> map, String header) throws IOException {
        OkHttpClient mClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .build();
        //MultipartBody.Builder builder = new MultipartBody.Builder();
        // builder.setType(MultipartBody.FORM);
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> e : map.entrySet()) {
            //builder.addFormDataPart(e.getKey(), e.getValue().toString());
            builder.add(e.getKey(), e.getValue().toString());
        }

        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("x-access-token",header)
                .build();
        okhttp3.Response response = mClient.newCall(request).execute();
        if (!response.isSuccessful())
            for (int i = 0; i < 3; i++) {
                response = mClient.newCall(request).execute();
                if (response.isSuccessful())
                    break;
            }
        return response.body().string();
    }

    public static String postJsonWithObject(String url, String allData, String header) throws IOException {
        OkHttpClient mClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .build();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,allData);
        Request request = new Request.Builder()
                .url(url)
                .method("PUT", body)
                .addHeader("x-access-token",header)
                .addHeader("Content-Type", "application/json")
                .build();


        okhttp3.Response response = mClient.newCall(request).execute();
        if (!response.isSuccessful())
            for (int i = 0; i < 3; i++) {
                response = mClient.newCall(request).execute();
                if (response.isSuccessful())
                    break;
            }
        return response.body().string();
    }

    public static String putJson(String url, Map<String, Object> map, String header) throws IOException {
        OkHttpClient mClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .build();
        //MultipartBody.Builder builder = new MultipartBody.Builder();
        // builder.setType(MultipartBody.FORM);
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> e : map.entrySet()) {
            //builder.addFormDataPart(e.getKey(), e.getValue().toString());
            builder.add(e.getKey(), e.getValue().toString());
        }

        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .method("PUT",body)
                .addHeader("x-access-token",header)
                .build();
        okhttp3.Response response = mClient.newCall(request).execute();
        if (!response.isSuccessful())
            for (int i = 0; i < 3; i++) {
                response = mClient.newCall(request).execute();
                if (response.isSuccessful())
                    break;
            }
        return response.body().string();
    }

    public static String deleteJson(String url, Map<String, Object> map, String header) throws IOException {
        OkHttpClient mClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .build();
        //MultipartBody.Builder builder = new MultipartBody.Builder();
        // builder.setType(MultipartBody.FORM);
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> e : map.entrySet()) {
            //builder.addFormDataPart(e.getKey(), e.getValue().toString());
            builder.add(e.getKey(), e.getValue().toString());
        }

        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .method("DELETE",body)
                .addHeader("x-access-token",header)
                .build();
        okhttp3.Response response = mClient.newCall(request).execute();
        if (!response.isSuccessful())
            for (int i = 0; i < 3; i++) {
                response = mClient.newCall(request).execute();
                if (response.isSuccessful())
                    break;
            }
        return response.body().string();
    }


    public static String putJsonWithoutHeader(String url, Map<String, Object> map, String header) throws IOException {
        OkHttpClient mClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .build();
        //MultipartBody.Builder builder = new MultipartBody.Builder();
        // builder.setType(MultipartBody.FORM);
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> e : map.entrySet()) {
            //builder.addFormDataPart(e.getKey(), e.getValue().toString());
            builder.add(e.getKey(), e.getValue().toString());
        }

        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .method("PUT",body)
                .build();
        okhttp3.Response response = mClient.newCall(request).execute();
        if (!response.isSuccessful())
            for (int i = 0; i < 3; i++) {
                response = mClient.newCall(request).execute();
                if (response.isSuccessful())
                    break;
            }
        return response.body().string();
    }



    public static String postJsonWithAppKey(String url, Map<String, Object> map, String header) throws IOException {
        OkHttpClient mClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .build();
        //MultipartBody.Builder builder = new MultipartBody.Builder();
        // builder.setType(MultipartBody.FORM);
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> e : map.entrySet()) {
            //builder.addFormDataPart(e.getKey(), e.getValue().toString());
            builder.add(e.getKey(), e.getValue().toString());
        }

        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("app-key",header)
                .build();
        okhttp3.Response response = mClient.newCall(request).execute();
        if (!response.isSuccessful())
            for (int i = 0; i < 3; i++) {
                response = mClient.newCall(request).execute();
                if (response.isSuccessful())
                    break;
            }
        return response.body().string();
    }



    public static String postJsonKey(String url, Map<String, Object> map, String header) throws IOException {
        OkHttpClient mClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .build();
        //MultipartBody.Builder builder = new MultipartBody.Builder();
        // builder.setType(MultipartBody.FORM);
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> e : map.entrySet()) {
            //builder.addFormDataPart(e.getKey(), e.getValue().toString());
            builder.add(e.getKey(), e.getValue().toString());
        }

        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        okhttp3.Response response = mClient.newCall(request).execute();
        if (!response.isSuccessful())
            for (int i = 0; i < 3; i++) {
                response = mClient.newCall(request).execute();
                if (response.isSuccessful())
                    break;
            }
        return response.body().string();
    }


    public static String GetJsonWithValue(String url, String headerKey, String headerValue, String staticHeader) throws
            IOException {
        OkHttpClient mClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .build();
        HttpUrl httpUrl = HttpUrl.parse(url);
        if (httpUrl == null)
            throw new IOException("Invalid Url");
        HttpUrl.Builder builder = httpUrl.newBuilder();

        Request request = new Request.Builder()
                .url(builder.build().url())
                .method("GET",null)
                .addHeader(headerKey,headerValue)
                .addHeader("x-access-token",staticHeader)
                .build();
        okhttp3.Response response = mClient.newCall(request).execute();

        if (!response.isSuccessful())
            for (int i = 0; i < 3; i++) {
                response = mClient.newCall(request).execute();
                if (response.isSuccessful())
                    break;
            }

        if (response.body() == null)
            return null;

        String bodyStr = response.body().string();
        return bodyStr;
    }

    public static String GetJson(String url, @Nullable HashMap<String, String> parameters, String header) throws
            IOException {
        OkHttpClient mClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .build();
        HttpUrl httpUrl = HttpUrl.parse(url);
        if (httpUrl == null)
            throw new IOException("Invalid Url");
        HttpUrl.Builder builder = httpUrl.newBuilder();
        if (parameters != null) {
            Iterator it = parameters.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                builder.addQueryParameter((String) pair.getKey(), (String) pair.getValue());
//                System.out.println(pair.getKey() + " = " + pair.getValue());
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
        Request request = new Request.Builder()
                .url(builder.build().url())
                .addHeader("x-access-token",header)
                .build();
        okhttp3.Response response = mClient.newCall(request).execute();

        if (!response.isSuccessful())
            for (int i = 0; i < 3; i++) {
                response = mClient.newCall(request).execute();
                if (response.isSuccessful())
                    break;
            }

        if (response.body() == null)
            return null;

        String bodyStr = response.body().string();
        return bodyStr;
    }

    public static String GetJsonAppKey(String url, Map<String, Object> map, String header) throws
            IOException {
        OkHttpClient mClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .build();
        HttpUrl httpUrl = HttpUrl.parse(url);
        if (httpUrl == null)
            throw new IOException("Invalid Url");
        HttpUrl.Builder builder = httpUrl.newBuilder();
        if (map != null) {
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                builder.addQueryParameter((String) pair.getKey(), (String) pair.getValue());
//                System.out.println(pair.getKey() + " = " + pair.getValue());
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
        Request request = new Request.Builder()
                .url(builder.build().url())
                .build();
        okhttp3.Response response = mClient.newCall(request).execute();

        if (!response.isSuccessful())
            for (int i = 0; i < 3; i++) {
                response = mClient.newCall(request).execute();
                if (response.isSuccessful())
                    break;
            }

        if (response.body() == null)
            return null;

        String bodyStr = response.body().string();
        return bodyStr;
    }

    public static String GetJsonAppKeyWithParam(String url, Map<String, Object> map, String header) throws
            IOException {
        OkHttpClient mClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .build();
        //MultipartBody.Builder builder = new MultipartBody.Builder();
        // builder.setType(MultipartBody.FORM);
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> e : map.entrySet()) {
            //builder.addFormDataPart(e.getKey(), e.getValue().toString());
            builder.add(e.getKey(), e.getValue().toString());
        }

        RequestBody body = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .method("GET",body)
                .build();
        okhttp3.Response response = mClient.newCall(request).execute();

        if (!response.isSuccessful())
            for (int i = 0; i < 3; i++) {
                response = mClient.newCall(request).execute();
                if (response.isSuccessful())
                    break;
            }

        if (response.body() == null)
            return null;

        String bodyStr = response.body().string();
        return bodyStr;
    }




}


