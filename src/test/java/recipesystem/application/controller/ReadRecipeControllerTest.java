package recipesystem.application.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import recipesystem.application.payload.PayloadRecipe;
import recipesystem.application.payload.ResponseRecipe;
import recipesystem.domain.model.Recipe;
import recipesystem.domain.service.RecipeService;


public class ReadRecipeControllerTest {

  @InjectMocks
  ReadRecipeController testTarget;
  @Mock
  RecipeService recipeService;
  
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void test_SuccessToReadRecipeFromId() {
    // setup
    Recipe recipe = createSuccessRecipe();
    when(recipeService.read(1)).thenReturn(recipe);
    
    // execute
    ResponseRecipe actual = testTarget.readRecipeFromId(1);
    
    // expected
    String expectedMessage = "Recipe details by id";
    
    // assert
    assertThat(actual.getMessage(), is(equalTo(expectedMessage)));
    PayloadRecipe actualRecipe = actual.getRecipe().get(0);
    assertThat(actualRecipe.getTitle(), is(equalTo(recipe.getTitle())));
    assertThat(actualRecipe.getMakingTime(), is(equalTo(recipe.getMakingTime())));
    assertThat(actualRecipe.getServes(), is(equalTo(recipe.getServes())));
    assertThat(actualRecipe.getIngredients(), is(equalTo(recipe.getIngredients())));
    assertThat(actualRecipe.getCost(), is(recipe.getCost().toString()));

  }

  private Recipe createSuccessRecipe() {
    return new Recipe(Long.valueOf(1), "チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000);
  }

}
