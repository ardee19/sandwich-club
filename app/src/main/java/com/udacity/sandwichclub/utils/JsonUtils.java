package com.udacity.sandwichclub.utils;

import android.util.EventLogTags;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    static String TAG = JsonUtils.class.getSimpleName();
    public static final String KEY_NAME = "name";
    public static final String KEY_ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String KEY_MAIN_NAME = "mainName";
    public static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_INGREDIENTS = "ingredients";
    public static final String KEY_IMAGE = "image";

    public static Sandwich parseSandwichJson(String json) {

        Log.d(TAG, "parseSandwichJson: " + json);

        try {
            JSONObject jsonReader = new JSONObject(json);
            JSONObject nameJSON = jsonReader.getJSONObject(KEY_NAME);
            JSONArray alsoKnownAsJSONArray = nameJSON.getJSONArray(KEY_ALSO_KNOWN_AS);

            String mainName = nameJSON.getString(KEY_MAIN_NAME);
            List<String> alsoKnownAs = new ArrayList<String>(alsoKnownAsJSONArray.length());
            for (int i=0; i < alsoKnownAsJSONArray.length(); i++ ) {
                alsoKnownAs.add(alsoKnownAsJSONArray.getString(i));
            }

            String placeOfOrigin = jsonReader.getString(KEY_PLACE_OF_ORIGIN);
            String description = jsonReader.getString(KEY_DESCRIPTION);

            JSONArray ingredientsJSONArray = jsonReader.getJSONArray(KEY_INGREDIENTS);
            List<String> ingredients = new ArrayList<String>(ingredientsJSONArray.length());

            Log.d(TAG, "ingredientsJSONArray count" + ingredientsJSONArray.length());
            Log.d(TAG, "ingredients list count" + ingredients.size());


            for (int i=0; i < ingredientsJSONArray.length(); i++ ) {
                ingredients.add(ingredientsJSONArray.getString(i));
            }

            String image = jsonReader.optString(KEY_IMAGE, "");

            return new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    //keys: name, alsoKnownAs, placeOfOrigin, description, image, ingredients
}
