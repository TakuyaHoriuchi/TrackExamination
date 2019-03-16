package recipesystem.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import recipesystem.domain.model.Recipe;

/**
 * {@link ReadRecipeService}の実装クラス.
 */
@Service
public class ReadRecipeServiceStub implements ReadRecipeService {

  /**
   * {@inheritDoc}.
   */
  @Override
  public Recipe read(Integer i) {
    return new Recipe(Long.valueOf(1), "チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<Recipe> readAll() {
    List<Recipe> recipeList = new ArrayList<>();
    recipeList.add(new Recipe(Long.valueOf(1), "チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス",        1000));
    recipeList.add(new Recipe(Long.valueOf(2), "オムライス",   "30分", "2人", "玉ねぎ,卵,スパイス,醤油",     700));
    recipeList.add(new Recipe(Long.valueOf(3), "トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450));
    return recipeList;
  }

}
