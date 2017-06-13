package com.dmitry.kartsev.yandextranslatetool.model.implementation;

import com.dmitry.kartsev.yandextranslatetool.model.ILanguagesDic;
import com.dmitry.kartsev.yandextranslatetool.model.deserializator.LanguageDeserealizationAdapter;
import com.dmitry.kartsev.yandextranslatetool.model.dto.LanguagesDicDTO;
import com.dmitry.kartsev.yandextranslatetool.model.rest.ApiClient;
import com.dmitry.kartsev.yandextranslatetool.model.rest.ApiInterface;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dmitry on 10.06.17.
 */

public class LanguagesDicImpl implements ILanguagesDic {
    GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(LanguagesDicDTO.class,
            new LanguageDeserealizationAdapter());
    Gson gson = gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    ApiInterface apiService = ApiClient.getClient(gson);

    @Override
    public Observable<LanguagesDicDTO> getLanguagesDic(String baseLanguage) {
        Map<String, String> map = new HashMap<>();
        map.put(ApiClient.PARAM_KEY, ApiClient.API_KEY);
        map.put(ApiClient.PARAM_LANG_KEY, baseLanguage);

        return apiService.getLanguagesDic(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
