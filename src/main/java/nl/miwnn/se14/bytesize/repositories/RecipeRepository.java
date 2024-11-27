package nl.miwnn.se14.bytesize.repositories;

import nl.miwnn.se14.bytesize.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByRecipeId(Long recipeId);
    Optional<List<Recipe>> findByRecipeTitle(String recipeTitle);
}
