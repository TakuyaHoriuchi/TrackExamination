package recipesystem.application.controller;

import java.util.ArrayList;

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

/**
 * レシピ情報を更新するクラス.
 */
@RestController
@RequestMapping(value = "recipes")
public class UpdateRecipeController {

  /**
   * 指定したIDのレシピを更新するメソッド.
   * @param id 更新対象レシピのID.
   * @param recipe レシピの更新情報.
   * @return 処理結果内容.
   */
  @PatchMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseRecipe updateRecipe(@PathVariable("id") int id,
                                     @RequestBody PayloadRequestRecipe payloadRecipe) {
    PayloadResponseRecipe responseRecipe = new PayloadResponseRecipe(null, "トマトスープレシピ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", "450");
    ResponseRecipe response = new ResponseRecipe();
    response.setMessage("Recipe successfully updated!");
    response.setRecipe(new ArrayList<>());
    response.getRecipe().add(responseRecipe);
    return response;
  }

}
