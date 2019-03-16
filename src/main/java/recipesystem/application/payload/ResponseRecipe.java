package recipesystem.application.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * 呼び出し元へ返却するレスポンスクラス.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseRecipe {
  /* メッセージ. */
  @JsonProperty("message")
  private String message;

  /* 必須パラメータ文言. */
  @JsonProperty("required")
  private String required;

  /* レシピ. */
  @JsonProperty("recipe")
  private List<PayloadResponseRecipe> recipe;

}
