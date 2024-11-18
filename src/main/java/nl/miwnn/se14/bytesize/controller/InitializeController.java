package nl.miwnn.se14.bytesize.controller;

import nl.miwnn.se14.bytesize.model.ByteSizeUser;
import nl.miwnn.se14.bytesize.model.Recipe;
import nl.miwnn.se14.bytesize.repositories.ByteSizeUserRepository;
import nl.miwnn.se14.bytesize.repositories.RecipeRepository;
import nl.miwnn.se14.bytesize.service.ByteSizeUserService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Heron
 * Purpose for the class
 */

@Controller
public class InitializeController {
    private final ByteSizeUserService byteSizeUserService;
    private final ByteSizeUserRepository byteSizeUserRepository;
    private final RecipeRepository recipeRepository;

    public InitializeController(ByteSizeUserService byteSizeUserService,
                                ByteSizeUserRepository byteSizeUserRepository, RecipeRepository recipeRepository) {
        this.byteSizeUserService = byteSizeUserService;
        this.byteSizeUserRepository = byteSizeUserRepository;
        this.recipeRepository = recipeRepository;
    }

    @EventListener
    private void seed(ContextRefreshedEvent ignoredEvent) {
        if (byteSizeUserRepository.findAll().isEmpty()) {
            initializeDB();
        }
    }

    private void initializeDB() {
        makeByteSizeUser("Admin", "Admin");

        Recipe indianChickenCurry = makeRecipe("Indian Chicken Curry", "Bla bla bla", "imageUrl");
        Recipe beefSteak = makeRecipe("Beef Steak", "Bla bla bla", "imageUrl");
        Recipe chickenLiverWrappedInBacon = makeRecipe("Chicken Liver Wrapped In Bacon", "Bla bla bla", "imageUrl");
        Recipe fruitSaladWithYoghurt = makeRecipe("Fruit Salad With Yoghurt", "Bla bla bla", "imageUrl");
        Recipe bestCheesecakeEver = makeRecipe("Best Cheesecake Ever", "Bla bla bla", "imageUrl");
    }

    private Recipe makeRecipe(String recipeTitle, String recipeDescription, String imageUrl) {
        Recipe recipe = new Recipe();
        recipe.setRecipeTitle(recipeTitle);
        recipe.setRecipeDescription(recipeDescription);
        recipe.setImageUrl(imageUrl);

        recipeRepository.save(recipe);
        return recipe;
    }

    private ByteSizeUser makeByteSizeUser(String username, String password) {
        ByteSizeUser user = new ByteSizeUser();
        user.setUsername(username);
        user.setPassword(password);
        byteSizeUserService.save(user);
        return user;
    }
}
