package com.dmitry.kartsev.yandextranslatetool.model.dto;

/**
 * Created by dmitry on 10.06.17.
 */

public class LanguageDTO {
    private String langCode;
    private String langName;

    public LanguageDTO(String langCode, String langName) {
        this.langCode = langCode;
        this.langName = langName;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }
}
