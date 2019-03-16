package recipesystem.domain.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import recipesystem.common.RecipeNotFoundException;
import recipesystem.domain.model.Recipe;
import recipesystem.infrastructure.model.RecipeEntity;
import recipesystem.infrastructure.repository.RecipeRepository;

/**
 * {@link ReadRecipeServiceImpl}のテスト.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReadRecipeServiceImplTest {
  
  ReadRecipeServiceImpl testTarget = new ReadRecipeServiceImpl();
  
  @Autowired
  private TestEntityManager entityManager;
  
  @Autowired
  RecipeRepository recipeRepository;

  private RecipeEntity recipe1 = new RecipeEntity(null, "チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000);
  private RecipeEntity recipe2 = new RecipeEntity(null, "オムライス", "30分", "2人", "玉ねぎ,卵,スパイス,醤油", 700);
  private RecipeEntity recipe3 = 
      new RecipeEntity(null, "トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
  
  /** テスト実施前のセットアップ. */
  @Before 
  public void setup() {
    testTarget.recipeRepos = recipeRepository;
    entityManager.persist(recipe1);
    entityManager.persist(recipe2);
    entityManager.persist(recipe3);
  }
  
  @Test
  public void test_SuccessToReadRecipeFromId() {
    // precheck
    if (recipeRepository.count() != 3) {
      fail("Tableの初期化に失敗しました。");
    }
    
    // execute
    Recipe actual = testTarget.read(1);
    
    // assert
    Recipe expected = createExpectedRecipe();
    assertThat(actual, is(samePropertyValuesAs(expected)));
  }
  
  @Test
  public void test_FailToReadRecipeFromId() {
    // precheck
    if (recipeRepository.count() != 3) {
      fail("Tableの初期化に失敗しました。");
    }
    
    // execute
    try {
      testTarget.read(100);
      fail("例外を投げる事に失敗しました。");
      
    } catch (RecipeNotFoundException e) {
      assertTrue("意図した例外を投げることに成功しました。", true);
      
    } catch (Exception e) {
      fail("意図しない例外が投げられました。" + e.getMessage());
    }
  }
  
  @Test
  public void test_FailToReadRecipeFromIdCausedByIdIsNull() {
    // precheck
    if (recipeRepository.count() != 3) {
      fail("Tableの初期化に失敗しました。");
    }
    
    // execute
    try {
      testTarget.read(null);
      fail("例外を投げる事に失敗しました。");
      
    } catch (RecipeNotFoundException e) {
      assertTrue("意図した例外を投げることに成功しました。", true);
      
    } catch (Exception e) {
      fail("意図しない例外が投げられました。" + e.getMessage());
    }
  }
  
  @Test
  public void test_SuccessToReadAllRecipes() {
    // precheck
    if (recipeRepository.count() != 3) {
      fail("Tableの初期化に失敗しました。");
    }
    
    // execute
    List<Recipe> actual = testTarget.readAll();
    
    // assert
    List<Recipe> expected = createAllRecipes();
    assertThat(actual, is(samePropertyValuesAs(expected)));
  }
  
  private Recipe createExpectedRecipe() {
    Recipe recipe = new Recipe();
    recipe.setTitle("チキンカレー");
    recipe.setMakingTime("45分");
    recipe.setServes("4人");
    recipe.setIngredients("玉ねぎ,肉,スパイス");
    recipe.setCost(1000);
    return recipe;
  }
  
  private List<Recipe> createAllRecipes() {
    Recipe firstRecipe = new Recipe();
    firstRecipe.setId(Long.valueOf(1));
    firstRecipe.setTitle("チキンカレー");
    firstRecipe.setMakingTime("45分");
    firstRecipe.setServes("4人");
    firstRecipe.setIngredients("玉ねぎ,肉,スパイス");
    firstRecipe.setCost(1000);
    
    Recipe secondRecipe = new Recipe();
    secondRecipe.setId(Long.valueOf(2));
    secondRecipe.setTitle("オムライス");
    secondRecipe.setMakingTime("30分");
    secondRecipe.setServes("2人");
    secondRecipe.setIngredients("玉ねぎ,卵,スパイス,醤油");
    secondRecipe.setCost(700);
    
    Recipe thirdRecipe = new Recipe();
    thirdRecipe.setId(Long.valueOf(3));
    thirdRecipe.setTitle("トマトスープ");
    thirdRecipe.setMakingTime("15分");
    thirdRecipe.setServes("5人");
    thirdRecipe.setIngredients("玉ねぎ, トマト, スパイス, 水");
    thirdRecipe.setCost(450);
    
    List<Recipe> recipes = new ArrayList<>();
    recipes.add(firstRecipe);
    recipes.add(secondRecipe);
    recipes.add(thirdRecipe);
    
    return recipes;
  }
  
}
