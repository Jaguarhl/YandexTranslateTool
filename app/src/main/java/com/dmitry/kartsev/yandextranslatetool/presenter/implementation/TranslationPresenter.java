package com.dmitry.kartsev.yandextranslatetool.presenter.implementation;

import android.util.Log;

import com.dmitry.kartsev.yandextranslatetool.model.ILanguagesDic;
import com.dmitry.kartsev.yandextranslatetool.model.ITranslation;
import com.dmitry.kartsev.yandextranslatetool.model.implementation.LanguagesDicImpl;
import com.dmitry.kartsev.yandextranslatetool.model.implementation.TranslationImpl;
import com.dmitry.kartsev.yandextranslatetool.model.dto.LanguagesDicDTO;
import com.dmitry.kartsev.yandextranslatetool.model.dto.TranslationAnswerDTO;
import com.dmitry.kartsev.yandextranslatetool.presenter.IPresenter;
import com.dmitry.kartsev.yandextranslatetool.presenter.vo.Languages;
import com.dmitry.kartsev.yandextranslatetool.view.IView;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by dmitry on 04.06.17.
 */

public class TranslationPresenter implements IPresenter {
    public static final String LOG_TAG = "TranslationPresenter";
    private ITranslation translationModel = new TranslationImpl();
    private ILanguagesDic lngDicModel = new LanguagesDicImpl();
    private IView view;
    private Subscription subscription = Subscriptions.empty();

    public TranslationPresenter(IView view) {
        this.view = view;
    }

    @Override
    public void setLanguageFrom(String language) {

    }

    @Override
    public void getLanguagesList() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        subscription = lngDicModel.getLanguagesDic(view.getSelectedLanguage())
                .subscribe(new Observer<LanguagesDicDTO>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                        Log.d(LOG_TAG, "Error in getLanguageDic: " + e.getMessage() + " " + e.getCause());
                    }

                    @Override
                    public void onNext(LanguagesDicDTO languagesDicDTO) {
                        if (languagesDicDTO != null && !languagesDicDTO.getListLanguages().isEmpty()) {
                            view.setLanguagesList(new Languages(languagesDicDTO.getListDirs(),
                                    languagesDicDTO.getListLanguages()));
                            Log.d(LOG_TAG, "Got lngs: " + languagesDicDTO.getListLanguages().toString());
                            Log.d(LOG_TAG, languagesDicDTO.getListLanguages().get("de").toString());
                        }
                    }
                });

    }

    @Override
    public void translateText() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        subscription = translationModel.translateText(view.getTextToTranslate())
                .subscribe(new Observer<TranslationAnswerDTO>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                        Log.d(LOG_TAG, e.getMessage() + " " + e.getCause());
                    }

                    @Override
                    public void onNext(TranslationAnswerDTO translationAnswerDTO) {
                        Log.d(LOG_TAG, "onNext called in translateText()");
                        if (translationAnswerDTO != null && !translationAnswerDTO.getText().isEmpty()) {
                            view.showTranslation(translationAnswerDTO);
                        } else {
                            view.showEmptyTranslation();
                        }
                    }
                });
    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
