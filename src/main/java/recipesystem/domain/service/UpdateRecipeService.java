package recipesystem.domain.service;

import recipesystem.domain.model.Recipe;

/**
 * リクエストの情報を元にレシピの更新を行うサービスクラス.
 */
public interface UpdateRecipeService {

  /**
   * 対象のIDや更新情報を使用して、レシピを更新する.
   * 
   * @param id 更新対象のレシピID.
   * @param recipe 更新情報.
   * @return 更新されたレシピ情報.
   * @throws recipesystem.exception.RecipeNotFoundException レシピが取得出来ない場合や更新処理に失敗した場合に発生.
   */
  Recipe update(Integer id, Recipe recipe);

}
