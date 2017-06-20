package com.dmitry.kartsev.yandextranslatetool.model;

import com.dmitry.kartsev.yandextranslatetool.model.dto.dictionary.DictionaryAnswer;

import rx.Observable;

/**
 * Created by dmitry on 19.06.17.
 * Interface for answers from dictionary service
 */

public interface IDictionary {
    Observable<DictionaryAnswer> getDictionaryAnswer(String textToTranslate, String lang);
}
