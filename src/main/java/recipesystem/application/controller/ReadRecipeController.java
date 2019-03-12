package recipesystem.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import recipesystem.application.payload.PayloadRecipe;
import recipesystem.application.payload.ResponseRecipe;
import recipesystem.application.payload.ResponseRecipeList;
import recipesystem.domain.model.Recipe;
import recipesystem.domain.service.ReadRecipeService;

/**
 * レシピ情報を取得するクラス.
 */
@RestController
@RequestMapping(value = "recipes")
public class ReadRecipeController {
  @Autowired
  private ReadRecipeService service;

  /**
   * 指定したIDのレシピを取得するメソッド.
   * @param id 取得対象レシピのID.
   * @return 処理結果内容.
   */
  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseRecipe readRecipeFromId(@PathVariable("id") Integer id) {
    Recipe readRecipe = service.read(id);
    return generateRecipeResponse(readRecipe);
  }
  
  /**
   * 全件レシピを取得するメソッド.
   * @return 処理結果内容.
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseRecipeList readRecipe() {
    List<Recipe> resultRecipeList = service.readAll();
    return generateRecipeListResponse(resultRecipeList);
  }
  
  private ResponseRecipe generateRecipeResponse(Recipe readRecipe) {
    ResponseRecipe response = new ResponseRecipe();
    response.setMessage("Recipe details by id");
    List<PayloadRecipe> recipeList = new ArrayList<>();
    PayloadRecipe responseRecipe = new PayloadRecipe();
    recipeList.add(responseRecipe);
    responseRecipe.setTitle(readRecipe.getTitle());
    responseRecipe.setMakingTime(readRecipe.getMakingTime());
    responseRecipe.setServes(readRecipe.getServes());
    responseRecipe.setIngredients(readRecipe.getIngredients());
    responseRecipe.setCost(readRecipe.getCost().toString()); 
    response.setRecipe(recipeList);
    return response;
  }

  private ResponseRecipeList generateRecipeListResponse(List<Recipe> resultRecipeList) {
    ResponseRecipeList response = new ResponseRecipeList();
    List<PayloadRecipe> payloadRecipeList = new ArrayList<>();
    mapResultToPayload(resultRecipeList, payloadRecipeList);
    response.setRecipes(payloadRecipeList);
    return response;
  }
  
  private void mapResultToPayload(List<Recipe> recipeList, List<PayloadRecipe> responseRecipeList) {
    for (Recipe recipe: recipeList) {
      PayloadRecipe responseRecipe = new PayloadRecipe();
      responseRecipe.setId(recipe.getId());
      responseRecipe.setTitle(recipe.getTitle());
      responseRecipe.setMakingTime(recipe.getMakingTime());
      responseRecipe.setServes(recipe.getServes());
      responseRecipe.setIngredients(recipe.getIngredients());
      responseRecipe.setCost(recipe.getCost().toString());
      responseRecipeList.add(responseRecipe);
    }
  }


}
