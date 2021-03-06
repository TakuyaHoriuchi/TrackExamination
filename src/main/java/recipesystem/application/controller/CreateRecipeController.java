package recipesystem.application.controller;

import static recipesystem.common.Constants.CREATE_FAILED;
import static recipesystem.common.Constants.CREATE_REQUIRED;
import static recipesystem.common.Constants.CREATE_SUCCESS;
import static recipesystem.common.Constants.LOG_CREATE_FAILED;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import recipesystem.application.payload.PayloadRequestRecipe;
import recipesystem.application.payload.PayloadResponseRecipe;
import recipesystem.application.payload.ResponseRecipe;
import recipesystem.common.FailToCreateRecipeException;
import recipesystem.domain.model.Recipe;
import recipesystem.domain.service.CreateRecipeService;

/**
 * レシピ情報を作成するクラス.
 */
@RestController
@RequestMapping(value = "recipes")
public class CreateRecipeController {

  @Autowired
  CreateRecipeService service;
  
  private Log log = LogFactory.getLog(CreateRecipeController.class);
  
  /**
   * 指定したIDのレシピを作成するメソッド.
   * 
   * @param request 作成レシピの情報.
   * @return 処理結果内容.
   */
  @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseRecipe createRecipe(@RequestBody PayloadRequestRecipe request) {
    Recipe requestRecipe = mapperRequestFromPayload(request);
    Recipe responseRecipe = null;
    ResponseRecipe response = new ResponseRecipe();
    
    try {
      responseRecipe = service.create(requestRecipe);
      
      PayloadResponseRecipe payloadResponseRecipe = mapperPayloadFromResponse(responseRecipe);
      List<PayloadResponseRecipe> recipe = new ArrayList<>();
      recipe.add(payloadResponseRecipe);
      response.setRecipe(recipe);
      response.setMessage(CREATE_SUCCESS);
      
    } catch (FailToCreateRecipeException e) {
      log.warn(LOG_CREATE_FAILED, e);
      response.setMessage(CREATE_FAILED);
      response.setRequired(CREATE_REQUIRED);
    }
    
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

  private Recipe mapperRequestFromPayload(PayloadRequestRecipe request) {
    Recipe result = new Recipe();
    result.setTitle(request.getTitle());
    result.setMakingTime(request.getMakingTime());
    result.setServes(request.getServes());
    result.setIngredients(request.getIngredients());
    result.setCost(request.getCost());
    return result;
  }


}
