package recipesystem.domain.service;

import org.springframework.beans.factory.annotation.Autowired;

import recipesystem.domain.model.Recipe;
import recipesystem.exception.RecipeNotFoundException;
import recipesystem.infrastructure.model.RecipeEntity;
import recipesystem.infrastructure.repository.RecipeRepository;

/**
 * {@link UpdateRecipeService}の実装クラス.
 */
public class UpdateRecipeServiceImpl implements UpdateRecipeService {
  @Autowired
  RecipeRepository recipeRepos;

  /**
   * {@inheritDoc}.
   */
  @Override
  public Recipe update(Integer id, Recipe recipe) {
    if (id > 5) {
      throw new RecipeNotFoundException();
    }
    RecipeEntity entity = new RecipeEntity();
    entity.setId(id);
    entity.setTitle(recipe.getTitle());
    entity.setMakingTime(recipe.getMakingTime());
    entity.setServes(recipe.getServes());
    entity.setIngredients(recipe.getIngredients());
    entity.setCost(recipe.getCost());
    recipeRepos.save(entity);
    return recipe;
  }

}
