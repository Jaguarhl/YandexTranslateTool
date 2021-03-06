package com.dmitry.kartsev.yandextranslatetool.model.rest;

import com.dmitry.kartsev.yandextranslatetool.model.dto.LanguageDetectedDTO;
import com.dmitry.kartsev.yandextranslatetool.model.dto.LanguagesDicDTO;
import com.dmitry.kartsev.yandextranslatetool.model.dto.TranslationAnswerDTO;
import com.dmitry.kartsev.yandextranslatetool.model.dto.dictionary.DictionaryAnswer;

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
    @FormUrlEncoded
    @POST("/api/v1.5/tr.json/detect")
    Observable<LanguageDetectedDTO> detectLanguage(@QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST("/api/v1.5/tr.json/translate")
    Observable<TranslationAnswerDTO> translate(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("/api/v1/dicservice/lookup")
    Observable<DictionaryAnswer> translateFull(@QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST("/api/v1.5/tr.json/getLangs")
    Observable<LanguagesDicDTO> getLanguagesDic(@FieldMap Map<String, String> map);
}
