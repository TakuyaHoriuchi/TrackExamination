package recipesystem.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipesystem.infrastructure.model.RecipeEntity;

/**
 * データベース接続用Repositoryクラス.
 */
@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {  
}
