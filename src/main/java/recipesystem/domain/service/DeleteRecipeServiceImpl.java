package recipesystem.domain.service;

import static java.util.Objects.isNull;
import static recipesystem.common.Constants.LOG_DELETE_FAIL_IS_CAUSED_BY_NULL_ID;
import static recipesystem.common.Constants.LOG_UNEXPECTED_DB_ACCESS_ERROR;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import recipesystem.common.RecipeNotFoundException;
import recipesystem.infrastructure.repository.RecipeRepository;

/**
 * {@link DeleteRecipeService}の実装クラス.
 */
@Service
public class DeleteRecipeServiceImpl implements DeleteRecipeService {
  @Autowired
  RecipeRepository recipeRepos;
  
  private Log log = LogFactory.getLog(DeleteRecipeServiceImpl.class);
  
  /**
   * {@inheritDoc}.
   */
  @Override
  public void delete(Integer id) {
    if (isNull(id)) {
      log.error(LOG_DELETE_FAIL_IS_CAUSED_BY_NULL_ID);
      throw new RecipeNotFoundException();
    }
    
    try {
      recipeRepos.deleteById(id);
      
    } catch (DataAccessException e) {
      log.error(LOG_UNEXPECTED_DB_ACCESS_ERROR, e);
      throw new RecipeNotFoundException(e);
    }
  }

}
