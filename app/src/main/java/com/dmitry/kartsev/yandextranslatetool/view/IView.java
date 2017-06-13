package com.dmitry.kartsev.yandextranslatetool.view;

import com.dmitry.kartsev.yandextranslatetool.model.dto.TranslationAnswerDTO;
import com.dmitry.kartsev.yandextranslatetool.presenter.vo.Languages;

import java.util.List;

/**
 * Created by dmitry on 04.06.17.
 */

public interface IView {
    String getTextToTranslate();
    String getSelectedLanguage();
    void setLanguagesList(Languages languages);
    void showError(String errMessage);
    void showTranslation(TranslationAnswerDTO answer);
    void showEmptyTranslation();
}
