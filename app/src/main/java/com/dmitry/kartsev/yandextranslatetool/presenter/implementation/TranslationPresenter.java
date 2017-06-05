package com.dmitry.kartsev.yandextranslatetool.presenter.implementation;

import android.util.Log;
import android.view.View;

import com.dmitry.kartsev.yandextranslatetool.model.ITranslation;
import com.dmitry.kartsev.yandextranslatetool.model.implementation.TranslationImpl;
import com.dmitry.kartsev.yandextranslatetool.model.pojo.TranslationAnswer;
import com.dmitry.kartsev.yandextranslatetool.presenter.IPresenter;
import com.dmitry.kartsev.yandextranslatetool.view.IView;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by dmitry on 04.06.17.
 */

public class TranslationPresenter implements IPresenter {
    public static final String LOG_TAG = "TranslationPresenter";
    private ITranslation model = new TranslationImpl();
    private IView view;
    private Subscription subscription = Subscriptions.empty();

    public TranslationPresenter(IView view) {
        this.view = view;
    }

    @Override
    public void setLanguageFrom(String language) {

    }

    @Override
    public void translateText() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        subscription = model.translateText(view.getTextToTranslate())
                .subscribe(new Observer<TranslationAnswer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                        Log.d(LOG_TAG, e.getMessage() + " " + e.getCause());
                    }

                    @Override
                    public void onNext(TranslationAnswer translationAnswer) {
                        Log.d(LOG_TAG, "onNext called in translateText()");
                        if (translationAnswer != null && !translationAnswer.getText().isEmpty()) {
                            view.showTranslation(translationAnswer);
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
