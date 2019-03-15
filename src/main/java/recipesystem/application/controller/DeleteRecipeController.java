package recipesystem.application.controller;

import recipesystem.application.payload.ResponseRecipe;

public class DeleteRecipeController {

  public ResponseRecipe deleteRecipe(int i) {
    ResponseRecipe response = new ResponseRecipe();
    response.setMessage("Recipe successfully removed!");
    return response;
  }

}
