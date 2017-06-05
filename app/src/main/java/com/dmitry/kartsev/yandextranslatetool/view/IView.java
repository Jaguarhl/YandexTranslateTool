package com.dmitry.kartsev.yandextranslatetool.view;

import com.dmitry.kartsev.yandextranslatetool.model.pojo.TranslationAnswer;

import java.util.List;

/**
 * Created by dmitry on 04.06.17.
 */

public interface IView {
    String getTextToTranslate();
    String getSelectedLanguage();
    void showError(String errMessage);
    void showTranslation(TranslationAnswer answer);
    void showEmptyTranslation();
}
