package recipesystem.application.controller;

import org.springframework.beans.factory.annotation.Autowired;

import recipesystem.application.payload.ResponseRecipe;
import recipesystem.domain.service.DeleteRecipeService;

public class DeleteRecipeController {
  @Autowired
  DeleteRecipeService service;
  
  public ResponseRecipe deleteRecipe(int i) {
    service.delete(i);
    ResponseRecipe response = new ResponseRecipe();
    response.setMessage("Recipe successfully removed!");
    return response;
  }

}
