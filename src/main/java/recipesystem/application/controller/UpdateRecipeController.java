package recipesystem.application.controller;

import static recipesystem.common.Constants.LOG_UPDATE_FAIL;
import static recipesystem.common.Constants.NOT_FOUND_RECIPE;
import static recipesystem.common.Constants.UPDATE_SUCCESS;

import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import recipesystem.application.payload.PayloadRequestRecipe;
import recipesystem.application.payload.PayloadResponseRecipe;
import recipesystem.application.payload.ResponseRecipe;
import recipesystem.common.RecipeNotFoundException;
import recipesystem.domain.model.Recipe;
import recipesystem.domain.service.UpdateRecipeService;

/**
 * レシピ情報を更新するクラス.
 */
@RestController
@RequestMapping(value = "recipes")
public class UpdateRecipeController {
  @Autowired
  private UpdateRecipeService service;
  
  private Log log = LogFactory.getLog(UpdateRecipeController.class);

  /**
   * 指定したIDのレシピを更新するメソッド.
   * @param id 更新対象レシピのID.
   * @param payloadRecipe レシピの更新情報.
   * @return 処理結果内容.
   */
  @PatchMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseRecipe updateRecipe(
      @PathVariable("id") Integer id, @RequestBody PayloadRequestRecipe payloadRecipe) {
    Recipe requestRecipe = mapperRequestFromPayload(payloadRecipe);
    Recipe responseRecipe = null;
    ResponseRecipe response = null;
    
    try {
      responseRecipe = service.update(id, requestRecipe);
      
      PayloadResponseRecipe responsePayloadRecipe = mapperPayloadFromResponse(responseRecipe);
      response = generateResponse(responsePayloadRecipe);
      
    } catch (RecipeNotFoundException e) {
      log.warn(LOG_UPDATE_FAIL, e);
      response = new ResponseRecipe();
      response.setMessage(NOT_FOUND_RECIPE);
    }
    
    return response;
  }

  private ResponseRecipe generateResponse(PayloadResponseRecipe responsePayloadRecipe) {
    ResponseRecipe response = new ResponseRecipe();
    response.setMessage(UPDATE_SUCCESS);
    response.setRecipe(new ArrayList<>());
    response.getRecipe().add(responsePayloadRecipe);
    return response;
  }

  private PayloadResponseRecipe mapperPayloadFromResponse(Recipe responseRecipe) {
    PayloadResponseRecipe result = new PayloadResponseRecipe();
    result.setTitle(responseRecipe.getTitle());
    result.setMakingTime(responseRecipe.getMakingTime());
    result.setServes(responseRecipe.getServes());
    result.setIngredients(responseRecipe.getIngredients());
    result.setCost(responseRecipe.getCost().toString());
    return result;
  }

  private Recipe mapperRequestFromPayload(PayloadRequestRecipe payloadRecipe) {
    Recipe result = new Recipe();
    result.setTitle(payloadRecipe.getTitle());
    result.setMakingTime(payloadRecipe.getMakingTime());
    result.setServes(payloadRecipe.getServes());
    result.setIngredients(payloadRecipe.getIngredients());
    result.setCost(payloadRecipe.getCost());
    return result;
  }

}
