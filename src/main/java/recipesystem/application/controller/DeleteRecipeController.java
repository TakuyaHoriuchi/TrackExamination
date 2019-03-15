package recipesystem.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import recipesystem.RecipeNotFoundException;
import recipesystem.application.payload.ResponseRecipe;
import recipesystem.domain.service.DeleteRecipeService;

/**
 * レシピを削除するクラス.
 */
@RestController
@RequestMapping(value = "recipes")
public class DeleteRecipeController {
  @Autowired
  DeleteRecipeService service;
  
  /**
   * 指定したIDのレシピを取得するメソッド.
   * @param id 削除対象レシピのID.
   * @return 処理結果内容.
   */
  @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseRecipe deleteRecipe(@PathVariable("id") Integer id) {
    ResponseRecipe response = new ResponseRecipe();
    try {
      service.delete(id);
      
      response.setMessage("Recipe successfully removed!");
      
    } catch (RecipeNotFoundException e) {
      response.setMessage("No Recipe found");
    }
    
    return response;
  }

}
