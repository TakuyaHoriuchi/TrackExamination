package recipesystem.domain.service;

import static java.util.Objects.isNull;
import static recipesystem.common.Constants.LOG_BE_SHORT_OF_CREATE_DATA;
import static recipesystem.common.Constants.LOG_UNEXPECTED_DB_ACCESS_ERROR;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import recipesystem.common.FailToCreateRecipeException;
import recipesystem.domain.model.Recipe;
import recipesystem.infrastructure.model.RecipeEntity;
import recipesystem.infrastructure.repository.RecipeRepository;

/**
 * {@link CreateRecipeService}の実装クラス.
 */
@Service
public class CreateRecipeServiceImpl implements CreateRecipeService {
  @Autowired
  RecipeRepository recipeRepos;
  
  private Log log = LogFactory.getLog(CreateRecipeServiceImpl.class);

  /**
   * {@inheritDoc}.
   */
  @Override
  public Recipe create(Recipe requestRecipe) {
    if (isIncorrectRecipeInfo(requestRecipe)) {
      log.info(LOG_BE_SHORT_OF_CREATE_DATA);
      throw new FailToCreateRecipeException();
    }
    
    RecipeEntity requestEntity = mapperEntityFromRequest(requestRecipe);
    RecipeEntity responseEntity;
    try {
      responseEntity = recipeRepos.save(requestEntity);
      
    } catch (DataAccessException e) {
      log.error(LOG_UNEXPECTED_DB_ACCESS_ERROR, e);
      throw new FailToCreateRecipeException(e);
    }
    
    return mapperResponseFromEntity(responseEntity);
  }

  
  private boolean isIncorrectRecipeInfo(Recipe requestRecipe) {
    return (isNull(requestRecipe.getTitle())
            || isNull(requestRecipe.getMakingTime())
            || isNull(requestRecipe.getServes())
            || isNull(requestRecipe.getIngredients())
            || isNull(requestRecipe.getCost()));
  }

  private RecipeEntity mapperEntityFromRequest(Recipe requestRecipe) {
    RecipeEntity result = new RecipeEntity();
    result.setTitle(requestRecipe.getTitle());
    result.setMakingTime(requestRecipe.getMakingTime());
    result.setServes(requestRecipe.getServes());
    result.setIngredients(requestRecipe.getIngredients());
    result.setCost(requestRecipe.getCost());
    return result;
  }

  private Recipe mapperResponseFromEntity(RecipeEntity responseEntity) {
    Recipe result = new Recipe();
    result.setTitle(responseEntity.getTitle());
    result.setMakingTime(responseEntity.getMakingTime());
    result.setServes(responseEntity.getServes());
    result.setIngredients(responseEntity.getIngredients());
    result.setCost(responseEntity.getCost());
    return result;
  }
}
