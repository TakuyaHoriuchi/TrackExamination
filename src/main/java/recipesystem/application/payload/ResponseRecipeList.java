package recipesystem.application.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * 呼び出し元へ返却する複数レシピを返却する用のレスポンスクラス.
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
