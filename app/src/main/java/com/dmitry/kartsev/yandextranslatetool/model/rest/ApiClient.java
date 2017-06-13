package com.dmitry.kartsev.yandextranslatetool.model.rest;

import com.google.gson.Gson;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by dmitry on 16.04.17.
 */

public class ApiClient {
    public static final String BASE_URL = "https://translate.yandex.net";
    public static final String API_KEY = "trnsl.1.1.20170412T172548Z.8672b2fd776ee197" +
            ".3d3884f331ba353dd8c600178109f77787abe792";
    public static final String PARAM_KEY = "key";
    public static final String PARAM_TEXT = "text";
    public static final String PARAM_LANG = "lang";
    public static final String PARAM_LANG_KEY = "ui";
    public static final int ANSWER_CODE_OK = 200;
    private static final boolean ENABLE_AUTH = false;
    private static final String AUTH_64 = "***"; //your code here
    private static Retrofit retrofit = null;

    public static ApiInterface getClient() {

        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        if (ENABLE_AUTH) builder.client(initOkHttp());

        ApiInterface apiInterface = builder.build().create(ApiInterface.class);
        return apiInterface;
    }

    private static OkHttpClient initOkHttp() {
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", "Basic " + AUTH_64)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        return httpClient;
    }

    public static ApiInterface getClient(Gson gson) {
        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        if (ENABLE_AUTH) builder.client(initOkHttp());

        ApiInterface apiInterface;
        return apiInterface = builder.build().create(ApiInterface.class);
    }
}
