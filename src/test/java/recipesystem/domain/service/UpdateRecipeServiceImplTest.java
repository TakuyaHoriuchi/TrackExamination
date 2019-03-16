package recipesystem.domain.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Optional;

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
 * {@link UpdateRecipeServiceImpl}のテスト.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UpdateRecipeServiceImplTest {
  
  UpdateRecipeServiceImpl testTarget = new UpdateRecipeServiceImpl();
  
  @Autowired
  private TestEntityManager entityManager;
  
  @Autowired
  RecipeRepository recipeRepository;

  private RecipeEntity recipe1 = new RecipeEntity(null, "チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000);
  private RecipeEntity recipe2 = new RecipeEntity(null, "オムライス", "30分", "2人", "玉ねぎ,卵,スパイス,醤油", 700);
  private RecipeEntity recipe3 = 
      new RecipeEntity(null, "トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
  
  /** テスト実施前のセットアップ. */
  @Before   public void setup() {
    testTarget.recipeRepos = recipeRepository;
    entityManager.persist(recipe1);
    entityManager.persist(recipe2);
    entityManager.persist(recipe3);
  }
  
  @Test
  public void test_SuccessToUpdateRecipe() {
    // precheck
    if (recipeRepository.count() != 3) {
      fail("Tableの初期化に失敗しました。");
    }
    Recipe requestRecipe = createRequestRecipe();
    
    // execute
    Recipe actual = testTarget.update(2, requestRecipe);
    
    // assert
    Recipe expected = createExpectedRequestRecipe();
    assertThat(actual, is(samePropertyValuesAs(expected)));
    Optional<RecipeEntity> findResult = recipeRepository.findById(2);
    assertThat(findResult.isPresent(), is(equalTo(true)));
    RecipeEntity readEntity = findResult.get();
    assertThat(recipeRepository.count(), is(equalTo(3L)));
    assertThat(readEntity.getId(), is(equalTo(2)));
    assertThat(readEntity.getTitle(), is(equalTo("トマトスープレシピ")));
    assertThat(readEntity.getMakingTime(), is(equalTo("15分")));
    assertThat(readEntity.getServes(), is(equalTo("5人")));
    assertThat(readEntity.getIngredients(), is(equalTo("玉ねぎ, トマト, スパイス, 水")));
    assertThat(readEntity.getCost(), is(equalTo(450)));
    
  }
  
  @Test
  public void test_FailToUpdateRecipe() {
    // precheck
    if (recipeRepository.count() != 3) {
      fail("Tableの初期化に失敗しました。");
    }
    Recipe requestRecipe = new Recipe();
    
    // execute
    try {
      testTarget.update(100, requestRecipe);
      fail("正しく例外が投げられませんでした。");
      
      // assert
    } catch (RecipeNotFoundException e) {
      assertTrue("意図した例外を投げることに成功しました。", true);
    }
    Optional<RecipeEntity> findResult = recipeRepository.findById(2);
    assertThat(findResult.isPresent(), is(equalTo(true)));
    RecipeEntity readEntity = findResult.get();
    assertThat(recipeRepository.count(), is(equalTo(3L)));
    assertThat(readEntity.getId(), is(equalTo(2)));
    assertThat(readEntity.getTitle(), is(equalTo("オムライス")));
    assertThat(readEntity.getMakingTime(), is(equalTo("30分")));
    assertThat(readEntity.getServes(), is(equalTo("2人")));
    assertThat(readEntity.getIngredients(), is(equalTo("玉ねぎ,卵,スパイス,醤油")));
    assertThat(readEntity.getCost(), is(equalTo(700)));
  }
  
  @Test
  public void test_FailToUpdateRecipeCausedByIdIsNull() {
    // precheck
    if (recipeRepository.count() != 3) {
      fail("Tableの初期化に失敗しました。");
    }
    
    // execute
    try {
      testTarget.update(null, new Recipe());
      fail("例外を投げる事に失敗しました。");
      
    } catch (RecipeNotFoundException e) {
      assertTrue("意図した例外を投げることに成功しました。", true);
      
    } catch (Exception e) {
      fail("意図しない例外が投げられました。" + e.getMessage());
    }
  }

  private Recipe createRequestRecipe() {
    Recipe requestRecipe = new Recipe();
    requestRecipe.setTitle("トマトスープレシピ");
    requestRecipe.setMakingTime("15分");
    requestRecipe.setServes("5人");
    requestRecipe.setIngredients("玉ねぎ, トマト, スパイス, 水");
    requestRecipe.setCost(450);
    return requestRecipe;
  }
  
  private Recipe createExpectedRequestRecipe() {
    Recipe expectedRecipe = new Recipe();
    expectedRecipe.setTitle("トマトスープレシピ");
    expectedRecipe.setMakingTime("15分");
    expectedRecipe.setServes("5人");
    expectedRecipe.setIngredients("玉ねぎ, トマト, スパイス, 水");
    expectedRecipe.setCost(450);
    return expectedRecipe;
  }

}

