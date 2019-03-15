package recipesystem.domain.service;

import org.springframework.stereotype.Service;

import recipesystem.domain.model.Recipe;

/**
 * {@link UpdateRecipeService}の実装クラス.
 */
@Service
public class UpdateRecipeServiceStub implements UpdateRecipeService {

  /**
   * {@inheritDoc}.
   */
  @Override
  public Recipe update(int id, Recipe recipe) {
    recipe.setId((long) id);
    return recipe;
  }

}