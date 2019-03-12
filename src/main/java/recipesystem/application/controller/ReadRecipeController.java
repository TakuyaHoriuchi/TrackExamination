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
  private ReadRecipeService recipeService;

  /**
   * 指定したIDのレシピを取得するメソッド.
   * @param id 取得対象レシピのID.
   * @return 処理結果内容.
   */
  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseRecipe readRecipeFromId(@PathVariable("id") Integer id) {
    Recipe readRecipe = recipeService.read(id);
    return generateRecipeResponse(readRecipe);
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

  public ResponseRecipeList readRecipe() {
    ResponseRecipeList response = new ResponseRecipeList();
    List<PayloadRecipe> recipeList = new ArrayList<>();
    recipeList.add(new PayloadRecipe(Long.valueOf(1), "チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス",        "1000"));
    recipeList.add(new PayloadRecipe(Long.valueOf(2), "オムライス",   "30分", "2人", "玉ねぎ,卵,スパイス,醤油",    "700"));
    recipeList.add(new PayloadRecipe(Long.valueOf(3), "トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", "450"));
    response.setRecipes(recipeList);
    return response;
  }


}
