package recipesystem.domain.service;

import java.util.List;
import recipesystem.domain.model.Recipe;

public interface ReadRecipeService {

  Recipe read(Integer i);

  List<Recipe> readAll();

}
