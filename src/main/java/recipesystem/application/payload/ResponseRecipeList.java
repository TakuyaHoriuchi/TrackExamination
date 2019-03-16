package recipesystem.application.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
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
