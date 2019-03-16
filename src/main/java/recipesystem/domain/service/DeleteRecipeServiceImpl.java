package recipesystem.domain.service;

import static java.util.Objects.isNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
  
  private Log log = LogFactory.getLog(DeleteRecipeServiceImpl.class);
  
  /**
   * {@inheritDoc}.
   */
  @Override
  public void delete(Integer id) {
    if (isNull(id)) {
      log.error("idがNullのため処理を終了します。");
      throw new RecipeNotFoundException();
    }
    
    try {
      recipeRepos.deleteById(id);
      
    } catch (DataAccessException e) {
      log.error("意図しないDBアクセスエラーが発生しました。", e);
      throw new RecipeNotFoundException(e);
    }
  }

}
