package com.dmitry.kartsev.yandextranslatetool.presenter.mappers;

import com.dmitry.kartsev.yandextranslatetool.model.dto.LanguagesDicDTO;
import com.dmitry.kartsev.yandextranslatetool.presenter.vo.Languages;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dmitry on 11.06.17.
 */

/*public class LanguagesListMapper implements Func1<LanguagesDicDTO, List<Languages>> {
    @Override
    public List<Languages> call(LanguagesDicDTO languagesDicDTOs) {
        List<Languages> lngList = Observable.from(languagesDicDTOs.getListLanguageDTOs())
        .map(lngDTO -> new Languages(lngDTO.getLangCode(), lngDTO.getLangName()))
        .toList()
        .toBlocking()
        .first();
        return lngList;
    }
}*/
