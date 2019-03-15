package recipesystem.application.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
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
import recipesystem.domain.service.CreateRecipeService;

/**
 * {@link CreateRecipeController}のテスト.
 */
public class CreateRecipeControllerTest {
  @InjectMocks
  CreateRecipeController testTarget;
  @Mock
  CreateRecipeService service;
  
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void test_SuccessToCreateRecipe() {
    // setup
    Recipe requestRecipe = createSuccessRequestRecipe();
    Recipe responseRecipe = createSuccessResponseRecipe();
    when(service.create(requestRecipe)).thenReturn(responseRecipe);
    
    // execute
    PayloadRequestRecipe request = new PayloadRequestRecipe();
    
    ResponseRecipe actual = testTarget.createRecipe(request);
    
    // expected
    String expectedMessage = "Recipe successfully created!";
    
    // assert
    assertThat(actual.getMessage(), is(equalTo(expectedMessage)));
    PayloadResponseRecipe actualRecipe = actual.getRecipe().get(0);
    assertThat(actualRecipe.getId(), is(nullValue()));
    assertThat(actualRecipe.getTitle(), is("トマトスープ"));
    assertThat(actualRecipe.getMakingTime(), is("15分"));
    assertThat(actualRecipe.getServes(), is("5人"));
    assertThat(actualRecipe.getIngredients(), is("玉ねぎ, トマト, スパイス, 水"));
    assertThat(actualRecipe.getCost(), is("450"));
  }

  private Recipe createSuccessRequestRecipe() {
    return new Recipe(null, "トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
  }

  private Recipe createSuccessResponseRecipe() {
    return new Recipe(Long.valueOf(4), "トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
  }

}
