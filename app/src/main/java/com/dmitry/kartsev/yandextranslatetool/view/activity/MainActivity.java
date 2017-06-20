package com.dmitry.kartsev.yandextranslatetool.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dmitry.kartsev.yandextranslatetool.R;
import com.dmitry.kartsev.yandextranslatetool.model.dto.TranslationAnswerDTO;
import com.dmitry.kartsev.yandextranslatetool.presenter.IPresenter;
import com.dmitry.kartsev.yandextranslatetool.presenter.implementation.TranslationPresenter;
import com.dmitry.kartsev.yandextranslatetool.presenter.vo.Languages;
import com.dmitry.kartsev.yandextranslatetool.view.IView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IView{

    public static final String LOG_TAG = "MainActivity";
    public String languageToTranslate = "ru";
    @Bind(R.id.editWord)
    EditText editToTranslate;
    @Bind(R.id.textTranslationVariants)
    TextView textTranslationVariants;
    @Bind(R.id.textObjectToTranslate)
    TextView textObjectToTranslate;
    @Bind(R.id.imageBtnClear)
    ImageButton btnClear;
    private IPresenter presenter;
    private Languages languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initEditTextBehaviour();
        presenter = new TranslationPresenter(this);
        presenter.getLanguagesList();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }

    @OnClick(R.id.imageBtnClear)
    void onSaveClick(View view) {
        editToTranslate.setText("");
    }

    private void initEditTextBehaviour() {
        editToTranslate.setOnKeyListener((v, keyCode, event) -> {
            if(event.getAction() == KeyEvent.ACTION_DOWN &&
                    (keyCode == KeyEvent.KEYCODE_ENTER))
            {
                // saving entered text before Enter key is pressed
                final String strTemporary = editToTranslate.getText().toString();
                // getting entered text language automatically

                makeToast(strTemporary, Toast.LENGTH_SHORT);
                presenter.translateText();
                return true;
            }
            return false;
        });
    }

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
    public void setLanguagesList(Languages languages) {
        this.languages = languages;
        Log.d(LOG_TAG, "Languages set^ " + languages.toString());
    }

    @Override
    public void showError(String errMessage) {
        makeToast(errMessage, Toast.LENGTH_LONG);
    }

    @Override
    public void showTranslation(TranslationAnswerDTO translationAnswerDTO) {
        String[] temp = translationAnswerDTO.getLang().split("-");
        Log.d(LOG_TAG, "temp is: " + temp[0] + " " + temp[1]);
        String languageDirection = languages.getLanguages().get(temp[0]) + " "
                + this.getResources().getString(R.string.direction_symbol) + " "
                + languages.getLanguages().get(temp[1]);
        this.setTitle(languageDirection);
        textObjectToTranslate.setText(getTextToTranslate());
        textTranslationVariants.setText(translationAnswerDTO.getText().toString().replace("[", "")
                .replace("]", ""));
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
