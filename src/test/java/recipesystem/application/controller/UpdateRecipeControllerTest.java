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

import recipesystem.application.payload.PayloadRequestRecipe;
import recipesystem.application.payload.PayloadResponseRecipe;
import recipesystem.application.payload.ResponseRecipe;
import recipesystem.domain.model.Recipe;
import recipesystem.domain.service.UpdateRecipeService;

/**
 * {@link ReadRecipeController}のテスト.
 */
public class UpdateRecipeControllerTest {

  @InjectMocks
  UpdateRecipeController testTarget;
  @Mock
  UpdateRecipeService service;
  
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void test_SuccessToUpdateRecipe() {
    // setup
    PayloadRequestRecipe payloadRecipe 
          = new PayloadRequestRecipe(null, "トマトスープレシピ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
    Recipe requestRecipe = createSuccessRequestRecipe();
    Recipe responseRecipe = createSuccessResponseRecipe();
    when(service.update(2, requestRecipe)).thenReturn(responseRecipe);
    
    // execute
    ResponseRecipe actual = testTarget.updateRecipe(2, payloadRecipe);
    
    // expected
    String expectedMessage = "Recipe successfully updated!";
    
    // assert
    assertThat(actual.getMessage(), is(equalTo(expectedMessage)));
    PayloadResponseRecipe actualRecipe = actual.getRecipe().get(0);
    assertThat(actualRecipe.getTitle(), is(equalTo(payloadRecipe.getTitle())));
    assertThat(actualRecipe.getMakingTime(), is(equalTo(payloadRecipe.getMakingTime())));
    assertThat(actualRecipe.getServes(), is(equalTo(payloadRecipe.getServes())));
    assertThat(actualRecipe.getIngredients(), is(equalTo(payloadRecipe.getIngredients())));
    assertThat(actualRecipe.getCost(), is(payloadRecipe.getCost().toString()));

  }

  private Recipe createSuccessResponseRecipe() {
    return new Recipe(Long.valueOf(2), "トマトスープレシピ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
  }

  private Recipe createSuccessRequestRecipe() {
    return new Recipe(null, "トマトスープレシピ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
  }

}
