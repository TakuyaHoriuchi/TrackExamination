package recipesystem.application.controller;

import java.util.ArrayList;
import java.util.List;

import recipesystem.application.payload.PayloadRecipe;
import recipesystem.application.payload.ResponseRecipe;

public class ReadRecipeController {

  public ResponseRecipe readRecipeFromId(int i) {
    ResponseRecipe response = new ResponseRecipe();
    response.setMessage("Recipe details by id");
    List<PayloadRecipe> recipeList = new ArrayList<>();
    PayloadRecipe responseRecipe = new PayloadRecipe();
    recipeList.add(responseRecipe);
    responseRecipe.setTitle("チキンカレー");
    responseRecipe.setMakingTime("45分");
    responseRecipe.setServes("4人");
    responseRecipe.setIngredients("玉ねぎ,肉,スパイス");
    responseRecipe.setCost("1000"); 
    response.setRecipe(recipeList);
    return response;
  }


}
