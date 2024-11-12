package nl.miwnn.se14.bytesize.repositories;

import nl.miwnn.se14.bytesize.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
