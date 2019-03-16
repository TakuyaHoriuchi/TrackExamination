package recipesystem.application.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * レシピ情報を返却する時の表示用クラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayloadResponseRecipe {
  /* レシピid. */
  @JsonProperty("id")
  private Long id;
  
  /* レシピ名. */
  @JsonProperty("title")
  private String title;
  
  /* 作成時間. */
  @JsonProperty("making_time")
  private String makingTime;
  
  /* 給仕人数. */
  @JsonProperty("serves")
  private String serves;
  
  /* 材料. */
  @JsonProperty("ingredients")
  private String ingredients;
  
  /* コスト. */
  @JsonProperty("cost")
  private String cost;

}
