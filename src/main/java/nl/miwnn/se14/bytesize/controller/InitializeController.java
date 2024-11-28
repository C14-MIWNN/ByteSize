package nl.miwnn.se14.bytesize.controller;

import nl.miwnn.se14.bytesize.model.ByteSizeUser;
import nl.miwnn.se14.bytesize.model.Recipe;
import nl.miwnn.se14.bytesize.repositories.ByteSizeUserRepository;
import nl.miwnn.se14.bytesize.repositories.RecipeRepository;
import nl.miwnn.se14.bytesize.service.ByteSizeUserService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

/**
 * @author Heron
 * Initializes some database
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
        ByteSizeUser admin = makeByteSizeUser("Admin", "AdminPassword", "ADMIN", "I am the Admin of the site!\nBOW BEFORE MY POWER!");
        ByteSizeUser heron = makeByteSizeUser("Heron", "123456", "USER", "I am a generic account made for testing purposes");
        ByteSizeUser yvonne = makeByteSizeUser("Yvonne", "123456", "USER", "Another generic account, please ignore!");
        ByteSizeUser jantje = makeByteSizeUser("Jantje", "123456", "USER", "Time to make some great food!");
        ByteSizeUser pietje = makeByteSizeUser("Pietje", "123456", "USER", "Jantje told me to get on here");

        Recipe indianChickenCurry = makeRecipe(
                "Indian Chicken Curry",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas at odio nec sem ultrices cursus.",
                "https://cdn.pixabay.com/photo/2022/06/07/20/52/curry-7249247_1280.jpg",
                "ingredients",
                admin,
                "instructions");
        Recipe beefSteak = makeRecipe(
                "Beef Steak",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas at odio nec sem ultrices cursus.",
                "https://cdn.pixabay.com/photo/2023/03/21/16/55/beef-7867701_1280.jpg",
                "ingredients\ningredients\ningredients",
                heron,
                "instructions");
        Recipe chickenLiverWrappedInBacon = makeRecipe(
                "Chicken Liver Wrapped In Bacon",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas at odio nec sem ultrices cursus.",
                "https://cdn.pixabay.com/photo/2019/04/20/12/08/chicken-liver-4141673_1280.jpg",
                "ingredients",
                yvonne,
                "instructions");
        Recipe fruitSaladWithYoghurt = makeRecipe(
                "Fruit Salad With Yoghurt",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas at odio nec sem ultrices cursus.",
                "https://cdn.pixabay.com/photo/2019/01/12/16/21/breakfast-3928800_1280.jpg",
                "ingredients",
                jantje,
                "instructions");
        Recipe bestCheesecakeEver = makeRecipe(
                "Best Cheesecake Ever",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas at odio nec sem ultrices cursus.",
                "https://cdn.pixabay.com/photo/2019/07/21/19/52/cheesecake-4353495_1280.jpg",
                "ingredients",
                pietje,
                "instructions");
    }

    private Recipe makeRecipe(String recipeTitle, String recipeDescription, String imageUrl, String ingredients, ByteSizeUser recipeCreator, String instructions) {
        Recipe recipe = new Recipe();
        recipe.setRecipeTitle(recipeTitle);
        recipe.setRecipeDescription(recipeDescription);
        recipe.setImageUrl(imageUrl);
        recipe.setIngredients(ingredients);
        recipe.setRecipeCreator(recipeCreator);
        recipe.setInstructions(instructions);

        recipeRepository.save(recipe);
        return recipe;
    }

    private ByteSizeUser makeByteSizeUser(String username, String password, String role, String userAboutMe) {
        ByteSizeUser user = new ByteSizeUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setUserAboutMe(userAboutMe);

        byteSizeUserService.save(user);
        return user;
    }
}
