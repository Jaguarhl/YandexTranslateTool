package com.dmitry.kartsev.yandextranslatetool.model;

import com.dmitry.kartsev.yandextranslatetool.model.dto.TranslationAnswerDTO;

import rx.Observable;

/**
 * Created by dmitry on 04.06.17.
 */

public interface ITranslation {
    Observable<TranslationAnswerDTO> translateText(String languageFrom);
}
