package com.dmitry.kartsev.yandextranslatetool.presenter.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dmitry on 11.06.17.
 */

public class Languages implements Serializable {
    private List<String> directions = new ArrayList<>();
    private Map<String, String> languages = new HashMap<>();

    public Languages(List<String> directions, Map<String, String> languages) {
        this.directions = directions;
        this.languages = languages;
    }

    public Map<String, String> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, String> languages) {
        this.languages = languages;
    }
    public List<String> getDirections() {
        return directions;
    }

    public void setDirections(List<String> directions) {
        this.directions = directions;
    }

    @Override
    public String toString() {
        return "Languages{" +
                "directions=" + directions +
                ", languages=" + languages +
                '}';
    }
}
