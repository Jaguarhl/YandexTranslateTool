package com.dmitry.kartsev.yandextranslatetool.model.rest;

import com.dmitry.kartsev.yandextranslatetool.model.pojo.LanguageDetected;
import com.dmitry.kartsev.yandextranslatetool.model.pojo.TranslationAnswer;
import com.dmitry.kartsev.yandextranslatetool.model.pojo.dictionary.DictionaryAnswer;

import java.util.Map;

import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import rx.Observable;

import retrofit.http.POST;
import retrofit.http.QueryMap;

/**
 * Created by dmitry on 13.04.17.
 */

public interface ApiInterface {
    @POST("/api/v1.5/tr.json/detect")
    Observable<LanguageDetected> detectLanguage(@QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST("/api/v1.5/tr.json/translate")
    Observable<TranslationAnswer> translate(@FieldMap Map<String, String> map);

    @POST("/api/v1/dicservice/lookup")
    Observable<DictionaryAnswer> translateFull(@QueryMap Map<String, String> map);
}
