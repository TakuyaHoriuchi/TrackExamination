package recipesystem.application.controller;

import static recipesystem.common.Constants.DELETE_SUCCESS;
import static recipesystem.common.Constants.LOG_DELETE_FAIL;
import static recipesystem.common.Constants.NOT_FOUND_RECIPE;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import recipesystem.application.payload.ResponseRecipe;
import recipesystem.common.RecipeNotFoundException;
import recipesystem.domain.service.DeleteRecipeService;

/**
 * レシピを削除するクラス.
 */
@RestController
@RequestMapping(value = "recipes")
public class DeleteRecipeController {
  

  @Autowired
  DeleteRecipeService service;
  
  private Log log = LogFactory.getLog(DeleteRecipeController.class);
  
  /**
   * 指定したIDのレシピを削除するメソッド.
   * @param id 削除対象レシピのID.
   * @return 処理結果内容.
   */
  @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseRecipe deleteRecipe(@PathVariable("id") Integer id) {
    ResponseRecipe response = new ResponseRecipe();
    try {
      service.delete(id);
      response.setMessage(DELETE_SUCCESS);
      
    } catch (RecipeNotFoundException e) {
      log.warn(LOG_DELETE_FAIL, e);
      response.setMessage(NOT_FOUND_RECIPE);
    }
    
    return response;
  }

}
