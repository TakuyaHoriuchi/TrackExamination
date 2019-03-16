package recipesystem.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import recipesystem.exception.RecipeNotFoundException;
import recipesystem.infrastructure.repository.RecipeRepository;

/**
 * {@link DeleteRecipeService}の実装クラス.
 */
@Service
public class DeleteRecipeServiceImpl implements DeleteRecipeService {
  @Autowired
  RecipeRepository recipeRepos;

  /**
   * {@inheritDoc}.
   */
  @Override
  public void delete(Integer id) {
    try {
      recipeRepos.deleteById(id);
      
    } catch (DataAccessException e) {
      throw new RecipeNotFoundException();
    }
  }

}
