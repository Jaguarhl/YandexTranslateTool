package com.dmitry.kartsev.yandextranslatetool.presenter;

/**
 * Created by dmitry on 04.06.17.
 */

public interface IPresenter {
    void setLanguageFrom(String language);
    void translateText();
    void onStop();
}
