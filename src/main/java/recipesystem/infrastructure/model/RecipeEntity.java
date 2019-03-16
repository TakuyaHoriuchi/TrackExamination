package recipesystem.infrastructure.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * レシピ情報を扱うエンティティクラス.
 */
@Data
@Entity
@Table(name = "recipes")
@AllArgsConstructor
@NoArgsConstructor
public class RecipeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  /* レシピid. */
  @Column(name = "id")
  private Integer id;
  
  /* レシピ名. */
  @Column(name = "title")
  private String title;
  
  /* 作成時間. */
  @Column(name = "making_time")
  private String makingTime;
  
  /* 給仕人数. */
  @Column(name = "serves")
  private String serves;
  
  /* 材料. */
  @Column(name = "ingredients")
  private String ingredients;
  
  /* コスト. */
  @Column(name = "cost")
  private Integer cost;
}
