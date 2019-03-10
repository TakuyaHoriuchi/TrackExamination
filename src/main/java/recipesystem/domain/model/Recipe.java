package recipesystem.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * レシピの情報を集めたドメインクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
  /* レシピid. */
  private Long id;
  
  /* レシピ名. */
  private String title;
  
  /* 作成時間. */
  private String makingTime;
  
  /* 給仕人数. */
  private String serves;
  
  /* 材料. */
  private String ingredients;
  
  /* コスト. */
  private Integer cost;
}