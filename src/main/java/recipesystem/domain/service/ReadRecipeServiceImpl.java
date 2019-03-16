package recipesystem.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import recipesystem.domain.model.Recipe;
import recipesystem.exception.RecipeNotFoundException;
import recipesystem.infrastructure.repository.RecipeRepository;

/**
 * {@link ReadRecipeService}の実装クラス.
 */
public class ReadRecipeServiceImpl implements ReadRecipeService {

  @Autowired
  RecipeRepository recipeRepos;

  /**
   * {@inheritDoc}.
   */
  @Override
  public Recipe read(int i) {
    if (i > 5) {
      throw new RecipeNotFoundException();
    }
    return new Recipe(Long.valueOf(1), "チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<Recipe> readAll() {
    return null;
  }

}
