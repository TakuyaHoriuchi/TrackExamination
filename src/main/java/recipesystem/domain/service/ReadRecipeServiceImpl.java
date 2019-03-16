package recipesystem.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import recipesystem.domain.model.Recipe;
import recipesystem.exception.RecipeNotFoundException;
import recipesystem.infrastructure.model.RecipeEntity;
import recipesystem.infrastructure.repository.RecipeRepository;

/**
 * {@link ReadRecipeService}の実装クラス.
 */
@Service
public class ReadRecipeServiceImpl implements ReadRecipeService {

  @Autowired
  RecipeRepository recipeRepos;

  /**
   * {@inheritDoc}.
   */
  @Override
  public Recipe read(Integer id) {
    Optional<RecipeEntity> result = null;
    try {
      result = recipeRepos.findById(id);

    } catch (DataAccessException e) {
      throw new RecipeNotFoundException(e);
    }

    if (!result.isPresent()) {
      throw new RecipeNotFoundException();
    }

    return mapperRecipeResponseFromResult(result);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<Recipe> readAll() {
    List<RecipeEntity> recipeList = recipeRepos.findAll();
    return mapperRecipeResponseListFromRecipeEntities(recipeList);
  }

  private Recipe mapperRecipeResponseFromResult(Optional<RecipeEntity> result) {
    RecipeEntity recipeEntity = result.get();
    Recipe response = new Recipe();
    response.setTitle(recipeEntity.getTitle());
    response.setMakingTime(recipeEntity.getMakingTime());
    response.setServes(recipeEntity.getServes());
    response.setIngredients(recipeEntity.getIngredients());
    response.setCost(recipeEntity.getCost());
    return response;
  }

  private List<Recipe> mapperRecipeResponseListFromRecipeEntities(List<RecipeEntity> recipeList) {
    List<Recipe> result = new ArrayList<>();
    for (RecipeEntity recipe : recipeList) {
      Recipe responseRecipe = new Recipe();
      responseRecipe.setId(Long.valueOf(recipe.getId()));
      responseRecipe.setTitle(recipe.getTitle());
      responseRecipe.setMakingTime(recipe.getMakingTime());
      responseRecipe.setServes(recipe.getServes());
      responseRecipe.setIngredients(recipe.getIngredients());
      responseRecipe.setCost(recipe.getCost());
      result.add(responseRecipe);
    }
    return result;
  }
}
