package recipesystem.domain.service;

/**
 * レシピ削除のサービスクラス.
 */
public interface DeleteRecipeService {

  /**
   * 対象のIDのレシピが存在すれば一覧から削除する.
   * 
   * @param id 削除対象のレシピID.
   * @throws recipesystem.common.RecipeNotFoundException レシピ削除に失敗した場合発生.
   */
  void delete(Integer id);

}
