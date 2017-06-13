package com.dmitry.kartsev.yandextranslatetool.model.dto;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LanguagesDicDTO {

    private List<String> listDirs = null;

    private Map<String, String> listLanguageDTOs;
    /*
    private List<LanguageDTO> listLanguageDTOs;
     */

    public LanguagesDicDTO(List<String> listDirs, Map<String, String> listLanguageDTOs) {
        this.listDirs = listDirs;
        this.listLanguageDTOs = listLanguageDTOs;
    }

    public List<String> getListDirs() {
        return listDirs;
    }

    public void setListDirs(List<String> listDirs) {
        this.listDirs = listDirs;
    }

    public Map<String, String> getListLanguages() {
        return listLanguageDTOs;
    }

    /*
    public List<LanguageDTO> getListLanguageDTOs() {
        return listLanguageDTOs;
    }

    public void setListLanguageDTOs(List<LanguageDTO> listLanguageDTOs) {
        this.listLanguageDTOs = listLanguageDTOs;
    }
    */
}