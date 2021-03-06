package recipesystem.domain.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import recipesystem.common.FailToCreateRecipeException;
import recipesystem.domain.model.Recipe;
import recipesystem.infrastructure.model.RecipeEntity;
import recipesystem.infrastructure.repository.RecipeRepository;

/**
 * {@link CreateRecipeServiceImpl}のテスト.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CreateRecipeServiceImplTest {
  
  CreateRecipeServiceImpl testTarget = new CreateRecipeServiceImpl();
  
  @Autowired
  private TestEntityManager entityManager;
  
  @Autowired
  RecipeRepository recipeRepository;

  private RecipeEntity recipe1 = new RecipeEntity(null, "tester1", "15分", "2人", "tester", 12345);
  private RecipeEntity recipe2 = new RecipeEntity(null, "tester2", "15分", "2人", "tester", 12345);
  
  /** テスト実施前のセットアップ. */
  @Before
  public void setup() {
    testTarget.recipeRepos = recipeRepository;
    entityManager.persist(recipe1);
    entityManager.persist(recipe2);
  }
  
  @Test
  public void test_SuccessToCreateRecipe() {
    // precheck
    if (recipeRepository.count() != 2) {
      fail("Tableの初期化に失敗しました。");
    }
    Recipe recipe = createSuccessRecipe();
    
    // execute
    Recipe actual = testTarget.create(recipe);
    
    // assert
    assertThat(actual, is(samePropertyValuesAs(recipe)));
    assertThat(recipeRepository.count(), is(equalTo(3L)));
  }
  
  @Test
  public void test_FailToCreateRecipe() {
    // precheck
    if (recipeRepository.count() != 2) {
      fail("Tableの初期化に失敗しました。");
    }
    Recipe recipe = new Recipe();
    
    // execute
    try {
      testTarget.create(recipe);
      
      // assert
      fail("正しく例外が投げられませんでした。");
    } catch (FailToCreateRecipeException e) {
      assertThat(recipeRepository.count(), is(equalTo(2L)));
    } catch (Exception e) {
      fail("意図しないErrorが発生しました。");
    }
  }
  
  private Recipe createSuccessRecipe() {
    Recipe requestRecipe = new Recipe();
    requestRecipe.setTitle("トマトスープ");
    requestRecipe.setMakingTime("15分");
    requestRecipe.setServes("5人");
    requestRecipe.setIngredients("玉ねぎ, トマト, スパイス, 水");
    requestRecipe.setCost(450);
    return requestRecipe;
  }
}