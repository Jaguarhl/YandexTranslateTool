package com.dmitry.kartsev.yandextranslatetool.model.implementation;

import com.dmitry.kartsev.yandextranslatetool.model.ILanguage;
import com.dmitry.kartsev.yandextranslatetool.model.pojo.LanguageDetected;
import com.dmitry.kartsev.yandextranslatetool.model.rest.ApiClient;
import com.dmitry.kartsev.yandextranslatetool.model.rest.ApiInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dmitry on 04.06.17.
 */

public class LanguageImpl implements ILanguage {
    ApiInterface apiService = ApiClient.getClient();

    @Override
    public Observable<LanguageDetected> getDetectedLanguage(String textToDetect) {
        Map<String, String> map = new HashMap<>();
        map.put(ApiClient.PARAM_KEY, ApiClient.API_KEY);
        map.put(ApiClient.PARAM_TEXT, textToDetect);

        return apiService.detectLanguage(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
