package com.dmitry.kartsev.yandextranslatetool.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dmitry.kartsev.yandextranslatetool.R;
import com.dmitry.kartsev.yandextranslatetool.model.pojo.TranslationAnswer;
import com.dmitry.kartsev.yandextranslatetool.presenter.IPresenter;
import com.dmitry.kartsev.yandextranslatetool.presenter.implementation.TranslationPresenter;
import com.dmitry.kartsev.yandextranslatetool.view.IView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IView{

    public static final String LOG_TAG = "MainActivity";
    public String languageToTranslate = "ru";
    @Bind(R.id.editWord)
    EditText editToTranslate;
    @Bind(R.id.textTranslationVariants)
    TextView textTranslationVariants;
    private IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initEditTextBehaviour();
        presenter = new TranslationPresenter(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }

    private void initEditTextBehaviour() {
        editToTranslate.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN &&
                        (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    // saving entered text before Enter key is pressed
                    final String strTemporary = editToTranslate.getText().toString();
                    // getting entered text language automatically

//                    // получим язык текста
//                    Map<String, String> mapLanguageFromJson = new HashMap<>();
//                    mapLanguageFromJson.put(ApiClient.PARAM_KEY, ApiClient.API_KEY);
//                    mapLanguageFromJson.put(ApiClient.PARAM_TEXT, strTemporary);
//
//                    getInputWordsLanguage(mapLanguageFromJson, strTemporary);


                    makeToast(strTemporary, Toast.LENGTH_SHORT);
                    presenter.translateText();
                    return true;
                }
                return false;
            }
        });
    }

    /*private void getInputWordsLanguage(Map<String, String> mapLanguageFromJson,
                                       final String strInput) {
        Call<LanguageDetected> callForLngDetect = apiService.detectLanguage(mapLanguageFromJson);

        callForLngDetect.enqueue(new Callback<LanguageDetected>() {
            @Override
            public void onResponse(Call<LanguageDetected> call, Response<LanguageDetected> response) {
                Log.d(LOG_TAG, response.body().getCode().toString() + " " +
                        response.body().getLang());
                if(response.body().getCode().equals(ApiClient.ANSWER_CODE_OK)) {
                    Log.d(LOG_TAG, "Detected language is " + response.body().getLang());
                    Map<String, String> mapJsonToTranslate = new HashMap<>();
                    mapJsonToTranslate.put(ApiClient.PARAM_KEY, ApiClient.API_KEY);
                    mapJsonToTranslate.put(ApiClient.PARAM_TEXT, strInput);
//                    mapJsonToTranslate.put(ApiClient.PARAM_LANG, response.body().getLang() + "-" +
//                            LanguageToTranslate);

                    getTranslatedText(mapJsonToTranslate);
                }
            }

            @Override
            public void onFailure(Call<LanguageDetected> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        getResources().getText(R.string.error_something_went_wrong),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getTranslatedText(Map<String, String> mapJsonToTranslate) {
        Call<DictionaryAnswer> callTranslate = apiService.translateFull(mapJsonToTranslate);

        callTranslate.enqueue(new Callback<DictionaryAnswer>() {
            @Override
            public void onResponse(Call<DictionaryAnswer> call,
                                   Response<DictionaryAnswer> response) {
                Log.d(LOG_TAG,response.body().getDef().get(0).toString()/* + " " + response.body()
                        .getLang() + response.body().getText()*///);
                /*if(response.body().getCode().equals(ApiClient.ANSWER_CODE_OK)) {
                    String answer = "";
                    for (int i = 0; i < response.body().getText().size(); i++) {
                        answer += response.body().getText().get(i) + "\n";
                    }
                    textTranslationVariants.setText(answer);
                }*//*
            }

            @Override
            public void onFailure(Call<DictionaryAnswer> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }*/

    @Override
    public String getTextToTranslate() {
        Log.d(LOG_TAG, "Send text " + editToTranslate.getText().toString() + " to translate to presenter.");
        return editToTranslate.getText().toString();
    }

    @Override
    public String getSelectedLanguage() {
        return languageToTranslate;
    }

    @Override
    public void showError(String errMessage) {
        makeToast(errMessage, Toast.LENGTH_LONG);
    }

    @Override
    public void showTranslation(TranslationAnswer translationAnswer) {
        textTranslationVariants.setText(translationAnswer.getText().toString());
    }

    @Override
    public void showEmptyTranslation() {
        textTranslationVariants.setText(this.getResources().getText(R.string.message_no_translation));
    }

    private void makeToast(String errMessage, int length) {
        Toast.makeText(this, errMessage, length)
                .show();
    }
}
