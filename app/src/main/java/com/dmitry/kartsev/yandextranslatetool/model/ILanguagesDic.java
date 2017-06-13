package com.dmitry.kartsev.yandextranslatetool.model;

import com.dmitry.kartsev.yandextranslatetool.model.dto.LanguagesDicDTO;

import rx.Observable;

/**
 * Created by dmitry on 10.06.17.
 */

public interface ILanguagesDic {
    Observable<LanguagesDicDTO> getLanguagesDic(String baseLanguage);
}
