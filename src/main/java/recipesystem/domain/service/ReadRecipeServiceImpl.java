package recipesystem.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import recipesystem.domain.model.Recipe;
import recipesystem.exception.RecipeNotFoundException;
import recipesystem.infrastructure.model.RecipeEntity;
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
  public Recipe read(int id) {
    Optional<RecipeEntity> result = null;
    try {
      result = recipeRepos.findById(id);

    } catch (IllegalArgumentException e) {
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
    List<Recipe> recipeList = new ArrayList<>();
    recipeList.add(new Recipe(Long.valueOf(1), "チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000));
    recipeList.add(new Recipe(Long.valueOf(2), "オムライス", "30分", "2人", "玉ねぎ,卵,スパイス,醤油", 700));
    recipeList.add(new Recipe(Long.valueOf(3), "トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450));
    return recipeList;
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

}
