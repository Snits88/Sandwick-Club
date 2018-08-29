package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        if(json == null){
            return null;
        }
        try {
            JSONObject jsonSandwich = new JSONObject(json);
            sandwich = new Sandwich();
            JSONObject name = jsonSandwich.getJSONObject("name");
            if(name != null){
                sandwich.setMainName(name.getString("mainName"));
                JSONArray arrayAliases = name.getJSONArray("alsoKnownAs");
                List<String> aliases = new ArrayList<>();
                if(arrayAliases != null){
                    for (int i = 0; i < arrayAliases.length(); i++){
                        aliases.add(arrayAliases.getString(i));
                    }
                }
                sandwich.setAlsoKnownAs(aliases);
            }
            sandwich.setPlaceOfOrigin(jsonSandwich.getString("placeOfOrigin"));
            sandwich.setDescription(jsonSandwich.getString("description"));
            sandwich.setImage(jsonSandwich.getString("image"));
            JSONArray ingredientsArray = jsonSandwich.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<>();
            if(ingredientsArray != null) {
                for (int i = 0; i < ingredientsArray.length(); i++) {
                    ingredients.add(ingredientsArray.getString(i));
                }
            }
            sandwich.setIngredients(ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }
}
