package com.dmitry.kartsev.yandextranslatetool.model;

import com.dmitry.kartsev.yandextranslatetool.model.pojo.TranslationAnswer;

import java.util.List;

import rx.Observable;

/**
 * Created by dmitry on 04.06.17.
 */

public interface ITranslation {
    Observable<TranslationAnswer> translateText(String languageFrom);
}
