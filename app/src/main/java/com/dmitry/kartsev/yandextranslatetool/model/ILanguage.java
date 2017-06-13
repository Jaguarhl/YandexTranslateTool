package com.dmitry.kartsev.yandextranslatetool.model;

import com.dmitry.kartsev.yandextranslatetool.model.dto.LanguageDetectedDTO;

import rx.Observable;

/**
 * Created by dmitry on 04.06.17.
 */

public interface ILanguage {
    Observable<LanguageDetectedDTO> getDetectedLanguage(String textToDetect);
}
