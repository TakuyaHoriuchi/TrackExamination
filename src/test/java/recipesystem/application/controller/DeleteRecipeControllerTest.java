package recipesystem.application.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import recipesystem.application.payload.ResponseRecipe;
import recipesystem.domain.service.DeleteRecipeService;
import recipesystem.exception.RecipeNotFoundException;

/**
 * {@link DeleteRecipeController}のテスト.
 */
public class DeleteRecipeControllerTest {

  @InjectMocks
  DeleteRecipeController testTarget;
  @Mock
  DeleteRecipeService service;
  
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void test_SuccessToDeleteRecipe() {
    // setup
    doNothing().when(service).delete(1);
    
    // execute
    ResponseRecipe actual = testTarget.deleteRecipe(1);
    
    // expected
    String expectedMessage = "Recipe successfully removed!";
    
    // assert
    assertThat(actual.getMessage(), is(equalTo(expectedMessage)));
  }
  
  @Test
  public void test_FailToDeleteRecipe() {
    // setup
    doThrow(new RecipeNotFoundException()).when(service).delete(1);
    
    // execute
    ResponseRecipe actual = testTarget.deleteRecipe(1);
    
    // expected
    String expectedMessage = "No Recipe found";
    
    // assert
    assertThat(actual.getMessage(), is(equalTo(expectedMessage)));
  }

}
