package recipesystem.domain.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
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

import recipesystem.exception.RecipeNotFoundException;
import recipesystem.infrastructure.model.RecipeEntity;
import recipesystem.infrastructure.repository.RecipeRepository;

/**
 * {@link DeleteRecipeServiceImpl}のテスト.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DeleteRecipeServiceImplTest {

  DeleteRecipeServiceImpl testTarget = new DeleteRecipeServiceImpl();

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  RecipeRepository recipeRepository;

  private RecipeEntity recipe1 = new RecipeEntity(null, "チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000);
  private RecipeEntity recipe2 
      = new RecipeEntity(null, "トマトスープレシピ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水油", 450);
  private RecipeEntity recipe3 
      = new RecipeEntity(null, "トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);

  /** テスト実施前のセットアップ. */
  @Before
  public void setup() {
    testTarget.recipeRepos = recipeRepository;
    entityManager.persist(recipe1);
    entityManager.persist(recipe2);
    entityManager.persist(recipe3);
  }

  @Test
  public void test_SuccessToDeleteRecipe() {
    // precheck
    if (recipeRepository.count() != 3) {
      fail("Tableの初期化に失敗しました。");
    }

    // execute
    try {
      testTarget.delete(2);
    } catch (Exception e) {
      fail("意図しない例外が投げられました。" + e.getMessage());
    }

    // assert
    assertThat(recipeRepository.count(), is(equalTo(2L)));
    Optional<RecipeEntity> findResult = recipeRepository.findById(2);
    assertThat(findResult.isPresent(), is(equalTo(false)));
  }

  @Test
  public void test_FailToDeleteRecipe() {
    // precheck
    if (recipeRepository.count() != 3) {
      fail("Tableの初期化に失敗しました。");
    }

    // execute
    try {
      testTarget.delete(100);
      fail("正しく例外が投げられませんでした。");

    } catch (RecipeNotFoundException e) {
      assertTrue("意図した例外を投げることに成功しました。", true);

    } catch (Exception e) {
      fail("意図しない例外が投げられました。" + e.getMessage());
    }

    // assert
    assertThat(recipeRepository.count(), is(equalTo(3L)));
  }
  
  @Test
  public void test_FailToDeleteRecipeCausedByIdIsNull() {
    // precheck
    if (recipeRepository.count() != 3) {
      fail("Tableの初期化に失敗しました。");
    }

    // execute
    try {
      testTarget.delete(null);
      fail("正しく例外が投げられませんでした。");

    } catch (RecipeNotFoundException e) {
      assertTrue("意図した例外を投げることに成功しました。", true);

    } catch (Exception e) {
      fail("意図しない例外が投げられました。" + e.getMessage());
    }

    // assert
    assertThat(recipeRepository.count(), is(equalTo(3L)));
  }
}
