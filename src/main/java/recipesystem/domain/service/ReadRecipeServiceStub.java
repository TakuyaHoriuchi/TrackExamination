package recipesystem.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import recipesystem.domain.model.Recipe;

@Service
public class ReadRecipeServiceStub implements ReadRecipeService {

  @Override
  public Recipe read(int i) {
    return new Recipe(Long.valueOf(1), "チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000);
  }

  @Override
  public List<Recipe> readAll() {
    // TODO Auto-generated method stub
    return null;
  }

}
