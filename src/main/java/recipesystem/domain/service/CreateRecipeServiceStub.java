package recipesystem.domain.service;

import static java.util.Objects.isNull;

import org.springframework.stereotype.Service;

import recipesystem.domain.model.Recipe;
import recipesystem.exception.FailToCreateRecipeException;

/**
 * {@link CreateRecipeService}の実装クラス.
 */
@Service
public class CreateRecipeServiceStub implements CreateRecipeService {

  /**
   * {@inheritDoc}.
   */
  @Override
  public Recipe create(Recipe requestRecipe) {
    if (isNull(requestRecipe.getTitle())
        || isNull(requestRecipe.getMakingTime())
        || isNull(requestRecipe.getServes())
        || isNull(requestRecipe.getIngredients())
        || isNull(requestRecipe.getCost())) {
      throw new FailToCreateRecipeException();
    }
    return requestRecipe;
  }

}
