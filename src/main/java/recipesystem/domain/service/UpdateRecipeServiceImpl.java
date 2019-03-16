package recipesystem.domain.service;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import recipesystem.domain.model.Recipe;
import recipesystem.exception.RecipeNotFoundException;
import recipesystem.infrastructure.model.RecipeEntity;
import recipesystem.infrastructure.repository.RecipeRepository;

/**
 * {@link UpdateRecipeService}の実装クラス.
 */
@Service
public class UpdateRecipeServiceImpl implements UpdateRecipeService {
  @Autowired
  RecipeRepository recipeRepos;

  private Log log = LogFactory.getLog(UpdateRecipeServiceImpl.class);
  
  /**
   * {@inheritDoc}.
   */
  @Override
  public Recipe update(Integer id, Recipe recipe) {
    Optional<RecipeEntity> readResult = readRecipe(id);
    
    RecipeEntity result = overwriteRecipe(recipe, readResult);
    
    return mapperResponseFromResultEntity(result);
  }

  /* IDからレシピ情報を取得するメソッド. */
  private Optional<RecipeEntity> readRecipe(Integer id) {
    Optional<RecipeEntity> readResult = null;
    try {
      readResult = recipeRepos.findById(id);

    } catch (DataAccessException e) {
      log.error("指定IDのレシピを取得中に、意図しないDBアクセスエラーが発生しました。", e);
      throw new RecipeNotFoundException(e);
    }

    if (!readResult.isPresent()) {
      log.info("指定IDのレシピが見つかりませんでした。");
      throw new RecipeNotFoundException();
    }
    return readResult;
  }

  /* IDから取得したレシピの情報を上書きするメソッド. */
  private RecipeEntity overwriteRecipe(Recipe recipe, Optional<RecipeEntity> readResult) {
    RecipeEntity entity = changeEntity(recipe, readResult);
    
    RecipeEntity result = null;
    try {
      result = recipeRepos.save(entity);
      
    } catch (DataAccessException e) {
      log.error("レシピ更新処理中に、意図しないDBアクセスエラーが発生しました。", e);
      throw new RecipeNotFoundException(e);
    }
    return result;
  }
  
  
  private RecipeEntity changeEntity(Recipe recipe, Optional<RecipeEntity> readResult) {
    RecipeEntity entity = readResult.get();
    entity.setTitle(recipe.getTitle());
    entity.setMakingTime(recipe.getMakingTime());
    entity.setServes(recipe.getServes());
    entity.setIngredients(recipe.getIngredients());
    entity.setCost(recipe.getCost());
    return entity;
  }

  private Recipe mapperResponseFromResultEntity(RecipeEntity result) {
    Recipe response = new Recipe();
    response.setTitle(result.getTitle());
    response.setMakingTime(result.getMakingTime());
    response.setServes(result.getServes());
    response.setIngredients(result.getIngredients());
    response.setCost(result.getCost());
    return response;
  }
}
