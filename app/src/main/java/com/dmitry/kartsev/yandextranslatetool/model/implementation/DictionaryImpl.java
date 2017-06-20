package com.dmitry.kartsev.yandextranslatetool.model.implementation;

import com.dmitry.kartsev.yandextranslatetool.model.IDictionary;
import com.dmitry.kartsev.yandextranslatetool.model.dto.dictionary.DictionaryAnswer;
import com.dmitry.kartsev.yandextranslatetool.model.rest.ApiClient;
import com.dmitry.kartsev.yandextranslatetool.model.rest.ApiInterface;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dmitry on 19.06.17.
 */

public class DictionaryImpl implements IDictionary {
    private ApiInterface apiService = ApiClient.getClient(ApiClient.BASE_DICTIONARY_URL);

    @Override
    public Observable<DictionaryAnswer> getDictionaryAnswer(String textToTranslate, String lang) {
        Map<String, String> map = new HashMap<>();
        map.put(ApiClient.PARAM_LANG, lang);
        map.put(ApiClient.PARAM_TEXT, textToTranslate);
        map.put(ApiClient.PARAM_KEY, ApiClient.API_KEY_DICTIONARY);

        return apiService.translateFull(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
