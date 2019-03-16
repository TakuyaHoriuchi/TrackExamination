package recipesystem.domain.service;

import java.util.List;
import recipesystem.domain.model.Recipe;

/**
 * レシピ情報を取得するサービスクラス.
 */
public interface ReadRecipeService {

  /**
   * IDを元にレシピ情報を取得する.
   * 
   * @param id 取得対象のレシピID.
   * @return 取得したレシピ情報.
   * @throws recipesystem.exception.RecipeNotFoundException レシピ情報が取得出来ない場合に発生.
   */
  Recipe read(Integer id);

  /**
   * 登録されているレシピ情報全てを取得する.
   * 
   * @return 登録されている全レシピ情報.
   * @throws recipesystem.exception.RecipeNotFoundException レシピ情報が取得出来ない場合に発生.
   */
  List<Recipe> readAll();

}
