package recipesystem.application.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import recipesystem.application.payload.PayloadResponseRecipe;
import recipesystem.application.payload.ResponseRecipe;
import recipesystem.application.payload.ResponseRecipeList;
import recipesystem.common.RecipeNotFoundException;
import recipesystem.domain.model.Recipe;
import recipesystem.domain.service.ReadRecipeService;

/**
 * {@link ReadRecipeController}のテスト.
 */
public class ReadRecipeControllerTest {

  @InjectMocks
  ReadRecipeController testTarget;
  @Mock
  ReadRecipeService service;
  
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void test_SuccessToReadRecipeFromId() {
    // setup
    Recipe recipe = createSuccessRecipe();
    when(service.read(1)).thenReturn(recipe);
    
    // execute
    ResponseRecipe actual = testTarget.readRecipeFromId(1);
    
    // expected
    String expectedMessage = "Recipe details by id";
    
    // assert
    assertThat(actual.getMessage(), is(equalTo(expectedMessage)));
    PayloadResponseRecipe actualRecipe = actual.getRecipe().get(0);
    assertThat(actualRecipe.getId(), is(nullValue()));
    assertThat(actualRecipe.getTitle(), is(equalTo(recipe.getTitle())));
    assertThat(actualRecipe.getMakingTime(), is(equalTo(recipe.getMakingTime())));
    assertThat(actualRecipe.getServes(), is(equalTo(recipe.getServes())));
    assertThat(actualRecipe.getIngredients(), is(equalTo(recipe.getIngredients())));
    assertThat(actualRecipe.getCost(), is(recipe.getCost().toString()));

  }
  
  @Test
  public void test_FailToReadRecipeFromId() {
    // setup
    doThrow(new RecipeNotFoundException()).when(service).read(100);
    
    // execute
    ResponseRecipe actual = testTarget.readRecipeFromId(100);
    
    // expected
    String expectedMessage = "No Recipe found";
    
    // assert
    assertThat(actual.getMessage(), is(equalTo(expectedMessage)));
    assertThat(actual.getRecipe(), is(nullValue()));

  }

  @Test
  public void test_SuccessToReadAllRecipe() {
    // setup
    List<Recipe> recipeList = createSuccessRecipeList();
    when(service.readAll()).thenReturn(recipeList);
    
    // execute
    ResponseRecipeList actual = testTarget.readRecipe();
    
    // assert
    assertThat(actual.getMessage(), is(nullValue()));
    List<PayloadResponseRecipe> recipes = actual.getRecipes();
    assertContents(
        recipes.get(0), Long.valueOf(1), "チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス",        "1000");
    assertContents(
        recipes.get(1), Long.valueOf(2), "オムライス",   "30分", "2人", "玉ねぎ,卵,スパイス,醤油",    "700");
    assertContents(
        recipes.get(2), Long.valueOf(3), "トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", "450");
    
  }
  
  @Test
  public void test_FailToReadAllRecipe() {
    // setup
    doThrow(new RecipeNotFoundException()).when(service).readAll();
    
    // execute
    ResponseRecipeList actual = testTarget.readRecipe();
    
    // expected
    String expectedErrorMessage = "Unexpected error is occured.";
    
    // assert
    assertThat(actual.getMessage(), is(equalTo(expectedErrorMessage)));
    assertThat(actual.getRecipes(), is(nullValue()));

  }

  private void assertContents(PayloadResponseRecipe firstPayloadRecipe,
                              Long id,
                              String title,
                              String makingTime,
                              String serves,
                              String ingredients,
                              String cost) {
    
    assertThat(firstPayloadRecipe.getId(), is(id));
    assertThat(firstPayloadRecipe.getTitle(), is(title));
    assertThat(firstPayloadRecipe.getMakingTime(), is(makingTime));
    assertThat(firstPayloadRecipe.getServes(), is(serves));
    assertThat(firstPayloadRecipe.getIngredients(), is(ingredients));
    assertThat(firstPayloadRecipe.getCost(), is(cost));
  }
  
  private List<Recipe> createSuccessRecipeList() {
    List<Recipe> recipeList = new ArrayList<>();
    recipeList.add(new Recipe(Long.valueOf(1), "チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス",        1000));
    recipeList.add(new Recipe(Long.valueOf(2), "オムライス",   "30分", "2人", "玉ねぎ,卵,スパイス,醤油",     700));
    recipeList.add(new Recipe(Long.valueOf(3), "トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450));
    return recipeList;
  }

  private Recipe createSuccessRecipe() {
    return new Recipe(Long.valueOf(1), "チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000);
  }

}
