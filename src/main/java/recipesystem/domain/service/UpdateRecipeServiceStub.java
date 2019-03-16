package recipesystem.domain.service;

import recipesystem.domain.model.Recipe;

/**
 * {@link UpdateRecipeService}の実装クラス.
 */
//@Service
public class UpdateRecipeServiceStub implements UpdateRecipeService {

  /**
   * {@inheritDoc}.
   */
  @Override
  public Recipe update(Integer id, Recipe recipe) {
    recipe.setId((long) id);
    return recipe;
  }

}
