package recipesystem.application.controller;

import org.springframework.beans.factory.annotation.Autowired;

import recipesystem.RecipeNotFoundException;
import recipesystem.application.payload.ResponseRecipe;
import recipesystem.domain.service.DeleteRecipeService;

public class DeleteRecipeController {
  @Autowired
  DeleteRecipeService service;
  
  public ResponseRecipe deleteRecipe(int i) {
    ResponseRecipe response = new ResponseRecipe();

    try {
      service.delete(i);
      response.setMessage("Recipe successfully removed!");

    } catch (RecipeNotFoundException e) {
      response.setMessage("No Recipe found");
    }
    
    return response;
  }

}
