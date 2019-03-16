package recipesystem.domain.service;

import org.springframework.stereotype.Service;

import recipesystem.exception.RecipeNotFoundException;

/**
 * {@link DeleteRecipeService}の実装クラス.
 */
@Service
public class DeleteRecipeServiceStub implements DeleteRecipeService {

  /**
   * {@inheritDoc}.
   */
  @Override
  public void delete(Integer i) {
    if (i < 5) {
      return;
    }
    throw new RecipeNotFoundException();
  }

}
