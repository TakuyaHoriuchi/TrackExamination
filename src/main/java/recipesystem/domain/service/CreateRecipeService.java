package recipesystem.domain.service;

import recipesystem.domain.model.Recipe;

public interface CreateRecipeService {

  Recipe create(Recipe requestRecipe);

}
