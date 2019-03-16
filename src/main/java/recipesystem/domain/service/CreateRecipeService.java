package recipesystem.domain.service;

import recipesystem.domain.model.Recipe;

/**
 * レシピを新規作成するサービスクラス.
 */
public interface CreateRecipeService {

  /**
   * 新規作成対象の情報からレシピを作成する.
   * 
   * @param requestRecipe 新規作成対象のレシピ情報.
   * @return 作成されたレシピ情報.
   * @throws recipesystem.common.FailToCreateRecipeException 新規作成出来なかった場合に発生.
   */
  Recipe create(Recipe requestRecipe);

}
