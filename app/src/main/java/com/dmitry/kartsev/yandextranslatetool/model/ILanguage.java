package com.dmitry.kartsev.yandextranslatetool.model;

import com.dmitry.kartsev.yandextranslatetool.model.pojo.LanguageDetected;

import java.util.List;

import rx.Observable;

/**
 * Created by dmitry on 04.06.17.
 */

public interface ILanguage {
    Observable<LanguageDetected> getDetectedLanguage(String textToDetect);
}
