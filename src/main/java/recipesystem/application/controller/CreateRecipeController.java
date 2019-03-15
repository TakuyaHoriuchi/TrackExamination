package recipesystem.application.controller;

import java.util.ArrayList;
import java.util.List;

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

/**
 * レシピ情報を取得するクラス.
 */
@RestController
@RequestMapping(value = "recipes")
public class CreateRecipeController {

  /**
   * 指定したIDのレシピを取得するメソッド.
   * @param id 取得対象レシピのID.
   * @return 処理結果内容.
   */
  @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseRecipe createRecipe(@RequestBody PayloadRequestRecipe request) {
    ResponseRecipe response = new ResponseRecipe();
    response.setMessage("Recipe successfully created!");
    List<PayloadResponseRecipe> recipe = new ArrayList<>();
    recipe.add(new PayloadResponseRecipe(null, "トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", "450"));
    response.setRecipe(recipe);
    return response;
  }


}
