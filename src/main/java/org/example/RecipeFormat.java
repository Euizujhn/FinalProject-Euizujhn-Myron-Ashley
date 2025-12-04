package org.example;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class RecipeFormat extends RecipeAPI {

    //Instructions Print
    public static String Instructions(String FoodKey){
      try {
        JSONObject FoodInformation = getFoodInfo(FoodKey);
      String instructions = (String) FoodInformation.get("strInstructions");
    return instructions;
    } catch (Exception e) {
     // e.printStackTrace();
    }
    return null;
    }

    //Ingredients Print
    public static String Ingredients(String FoodKey){
        try {
            JSONObject FoodIngredients = getFoodInfo(FoodKey);
            String empty = "";

            for (int i = 1; i <= 20; i++){
                String List = (String) FoodIngredients.get("strIngredient" + i);
                String Measure = (String) FoodIngredients.get("strMeasure" + i);
                String combine =  List + ": " + Measure;
                System.out.println(combine);
                if (List.equals(empty)){
                    return combine;
                }
            }

        } catch (Exception e) {
            // e.printStackTrace();
        }
        return null;
    }


    //Ingredients GUI Output
    public static String IngredientsGUI(String FoodKey){
        try{
            JSONObject FoodIngredients = getFoodInfo(FoodKey);
            String empty = "";
            ArrayList<String> IngredientList = new ArrayList<>();
            String Area = (String) FoodIngredients.get("strArea");
            String FullMeal = (String) FoodIngredients.get("strMeal");
            String Category = (String) FoodIngredients.get("strCategory");
            String Origin = "Ethnicity : " + Area;
            String Type = "Type: " + Category;
            for (int i = 1; i <= 20; i++) {
                String List = (String) FoodIngredients.get("strIngredient" + i);
                String Measure = (String) FoodIngredients.get("strMeasure" + i);
                String combine =  List + ": " + Measure + "\n";
                IngredientList.add(combine);
                if (List.equals(empty)){
                    return FullMeal + "\n" + Origin + "\n" + Type + "\n" + "\n" + String.valueOf(IngredientList);
                }
            }
        } catch (Exception e) {
            String empty = "Recipe is not within the API";
            return empty;
        }
        return null;
    }


}
