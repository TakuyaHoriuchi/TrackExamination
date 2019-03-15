package recipesystem.application.payload;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 複数レシピのレスポンスクラス.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseRecipeList {
  /* メッセージ. */
  @JsonProperty("message")
  private String message;

  /* レシピ. */
  @JsonProperty("recipes")
  private List<PayloadResponseRecipe> recipes;

}
