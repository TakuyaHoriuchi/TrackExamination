package recipesystem.domain.service;

import java.util.Objects;

import recipesystem.domain.model.Recipe;
import recipesystem.exception.FailToCreateRecipeException;
import recipesystem.infrastructure.model.RecipeEntity;
import recipesystem.infrastructure.repository.RecipeRepository;

/**
 * {@link CreateRecipeService}の実装クラス.
 */
public class CreateRecipeServiceImpl implements CreateRecipeService {

  RecipeRepository recipeRepos;

  /**
   * {@inheritDoc}.
   */
  @Override
  public Recipe create(Recipe requestRecipe) {
    if (Objects.isNull(requestRecipe.getTitle())) {
      throw new FailToCreateRecipeException();
    } else {
      recipeRepos.save(new RecipeEntity());
    }
    return requestRecipe;
  }

}
