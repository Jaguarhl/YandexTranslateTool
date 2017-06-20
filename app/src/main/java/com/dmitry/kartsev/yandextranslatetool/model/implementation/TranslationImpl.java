package com.dmitry.kartsev.yandextranslatetool.model.implementation;

import android.util.Log;

import com.dmitry.kartsev.yandextranslatetool.model.ITranslation;
import com.dmitry.kartsev.yandextranslatetool.model.dto.TranslationAnswerDTO;
import com.dmitry.kartsev.yandextranslatetool.model.rest.ApiClient;
import com.dmitry.kartsev.yandextranslatetool.model.rest.ApiInterface;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dmitry on 04.06.17.
 */

public class TranslationImpl implements ITranslation {

    public static final String LOG_TAG = "TranslationImpl";
    ApiInterface apiService = ApiClient.getClient(ApiClient.BASE_URL);

    @Override
    public Observable<TranslationAnswerDTO> translateText(String toTranslate) {
        Map<String, String> map = new HashMap<>();
        map.put(ApiClient.PARAM_TEXT, toTranslate);
        map.put(ApiClient.PARAM_LANG, "ru");
        map.put(ApiClient.PARAM_KEY, ApiClient.API_KEY);
        Log.d(LOG_TAG, map.toString());

        return apiService.translate(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
