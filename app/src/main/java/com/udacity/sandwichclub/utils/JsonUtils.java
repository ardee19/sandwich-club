package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    static String TAG = "RD-Debug";

    public static Sandwich parseSandwichJson(String json) {

        Log.d(TAG, "parseSandwichJson: " + json);

        try {
            JSONObject jsonReader = new JSONObject(json);
            JSONObject nameJSON = jsonReader.getJSONObject("name");
            JSONArray alsoKnownAsJSONArray = nameJSON.getJSONArray("alsoKnownAs");

            String mainName = nameJSON.getString("mainName");
            List<String> alsoKnownAs = new ArrayList<String>(alsoKnownAsJSONArray.length());
            for (int i=0; i < alsoKnownAsJSONArray.length(); i++ ) {
                alsoKnownAs.add(alsoKnownAsJSONArray.getString(i));
            }

            String placeOfOrigin = jsonReader.getString("placeOfOrigin");
            String description = jsonReader.getString("description");

            JSONArray ingredientsJSONArray = jsonReader.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<String>(ingredientsJSONArray.length());

            Log.d(TAG, "ingredientsJSONArray count" + ingredientsJSONArray.length());
            Log.d(TAG, "ingredients list count" + ingredients.size());


            for (int i=0; i < ingredientsJSONArray.length(); i++ ) {
                ingredients.add(ingredientsJSONArray.getString(i));
            }

            String image = jsonReader.getString("image");

            return new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    //keys: name, alsoKnownAs, placeOfOrigin, description, image, ingredients
}
