package com.dmitry.kartsev.yandextranslatetool.model.deserializator;

import android.util.Log;

import com.dmitry.kartsev.yandextranslatetool.model.dto.LanguageDTO;
import com.dmitry.kartsev.yandextranslatetool.model.dto.LanguagesDicDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dmitry on 11.06.17.
 */

public class LanguageDeserealizationAdapter implements JsonDeserializer<LanguagesDicDTO> {

    public static final String JSON_KEY_LANGS = "langs";
    public static final String LOG_TAG = "LngDesializer";
    public static final String JSON_KEY_DIRS = "dirs";

    @Override
    public LanguagesDicDTO deserialize(JsonElement jsonElement, Type typeOfT,
                                       JsonDeserializationContext context) throws JsonParseException {
        List<String> dirsList = new ArrayList<>();
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray dirs = jsonObject.getAsJsonArray(JSON_KEY_DIRS);
        for (JsonElement dir : dirs) {
            dirsList.add(dir.getAsString());
        }

        JsonObject jsonObjectLang = jsonObject.get(JSON_KEY_LANGS).getAsJsonObject();
        Map<String, String> lngsMap = new HashMap<>();
        String[] pairs = jsonObjectLang.toString().replace("\"", "").replace("}", "").replace("{", "").split(",");
        for (String pair: pairs) {
            String[] parts = pair.split(":");
            lngsMap.put(parts[0], parts[1]);
        }

        return new LanguagesDicDTO(dirsList, lngsMap);
    }
}
