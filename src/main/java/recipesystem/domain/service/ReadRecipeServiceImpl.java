package recipesystem.domain.service;

import static recipesystem.common.Constants.LOG_RECIPE_IS_NOT_FOUND_AT_ID;
import static recipesystem.common.Constants.LOG_UNEXPECTED_DB_ACCESS_ERROR;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import recipesystem.common.RecipeNotFoundException;
import recipesystem.domain.model.Recipe;
import recipesystem.infrastructure.model.RecipeEntity;
import recipesystem.infrastructure.repository.RecipeRepository;

/**
 * {@link ReadRecipeService}の実装クラス.
 */
@Service
public class ReadRecipeServiceImpl implements ReadRecipeService {

  @Autowired
  RecipeRepository recipeRepos;
  
  private Log log = LogFactory.getLog(ReadRecipeServiceImpl.class);

  /**
   * {@inheritDoc}.
   */
  @Override
  public Recipe read(Integer id) {
    Optional<RecipeEntity> result = null;
    try {
      result = recipeRepos.findById(id);

    } catch (DataAccessException e) {
      log.error(LOG_UNEXPECTED_DB_ACCESS_ERROR, e);
      throw new RecipeNotFoundException(e);
    }

    if (!result.isPresent()) {
      log.info(LOG_RECIPE_IS_NOT_FOUND_AT_ID);
      throw new RecipeNotFoundException();
    }

    return mapperRecipeResponseFromResult(result);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<Recipe> readAll() {
    List<RecipeEntity> recipeList = null;
    try {
      recipeList = recipeRepos.findAll();
      
    } catch (DataAccessException e) {
      log.error(LOG_UNEXPECTED_DB_ACCESS_ERROR, e);
      throw new RecipeNotFoundException(e);
    }
    
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
