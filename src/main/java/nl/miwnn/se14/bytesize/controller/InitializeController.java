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
                "2 pounds boneless chicken breast\n" +
                        "2 teaspoons salt\nhalve up cooking oil\n" +
                        "1,5 cups chopped onion\n" +
                        "1 tablespoon minced garlic\n" +
                        "1 tablespoon curry powder\n" +
                        "1 teaspoon ground tumeric\n" +
                        "1 teaspoon cayenne pepper\n" +
                        "1 tablespoon water\n" +
                        "15 ounce can crushed tomatoes\n" +
                        "1 cup plain yoghurt\n" +
                        "1 teaspoon salt\n" +
                        "halve cup water\n" +
                        "t teaspoon garam masala\n" +
                        "1 tablespoon chopped fresh cilanto\n" +
                        "1 tablespoon fresh lemon juice\n",
                admin,
                "Step 1\n" +
                        "Gather all ingredients.\n" +
                        " \n" +
                        "Step 2\n" +
                        "Sprinkle the chicken breasts with 2 teaspoons salt. \n" +
                        "Heat oil in a large skillet over high heat; \n" +
                        "partially cook the chicken in the hot oil in batches until completely browned on all sides. \n" +
                        "Transfer browned chicken breasts to a plate and set aside.\n" +
                        " \n" +
                        "Step 3\n" +
                        "Reduce the heat to medium and add onion, garlic, and ginger to the oil remaining in the skillet. \n" +
                        "Cook and stir until onion turns soft and translucent, 5 to 8 minutes. \n" +
                        "Stir curry powder, cumin, turmeric, coriander, cayenne, and 1 tablespoon of water into the onion mixture; \n" +
                        "allow to heat together for about 1 minute while stirring.\n" +
                        " \n" +
                        "Step 4\n" +
                        "Add tomatoes, yogurt, 1 tablespoon chopped cilantro, and 1 teaspoon salt to the mixture; \n" +
                        "stir to combine.\n" +
                        " \n" +
                        "Step 5\n" +
                        "Return chicken breast to the skillet along with any juices on the plate. \n" +
                        "Pour in 1/2 cup water and bring to a boil, turning the chicken to coat with the sauce. \n" +
                        "Sprinkle garam masala and 1 tablespoon cilantro over the chicken.\n" +
                        " \n" +
                        "Step 6\n" +
                        "Cover the skillet and simmer until chicken breasts are no longer pink in the center \n" +
                        "and the juices run clear, about 20 minutes. \n" +
                        "An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C). \n" +
                        "Drizzle with lemon juice to serve.");
        Recipe beefSteak = makeRecipe(
                "Beef Steak",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. \n" +
                        "Maecenas at odio nec sem ultrices cursus.",
                "https://cdn.pixabay.com/photo/2023/03/21/16/55/beef-7867701_1280.jpg",
                "1 tsp Dijon mustard\n" +
                        "halve tsp minced garlic\n" +
                        "halve tsp onion powder\n" +
                        "1 tbsp oil\n" +
                        "1 tbsn Worcestershire sauce\n" +
                        "1 tbsp balsamic vindegar\n" +
                        "black pepper\n",
                heron,
                "1. Mix together mustard, garlic and onion powder. \n" +
                        "Then mix in remaining ingredients.\n" +
                        " \n" +
                        "2. Place beef in a ziplock bag with Marinade and marinade overnight (12 - 24 hours).\n" +
                        " \n" +
                        "3. Remove from the fridge 30 minutes before cooking to bring to room temperature - key for even cooking of steaks. \n" +
                        "Shake off excess marinade.\n" +
                        " \n" +
                        "4. Brush BBQ Grills with oil, then heat on high heat until is really hot - you should see wisps of smoke. \n" +
                        "Or heat a heavy based skillet on high until very hot, then add oil - it will heat almost instantly.\n" +
                        " \n" +
                        "5. Add steaks. For 2cm / 3/4 inch thick steaks, cook the first side for 2 minutes, \n" +
                        "then turn and cook the other side for 2 minutes (medium rare 52°C/125°F, \n" +
                        "chart below for other doneness temps). (Note 3)\n" +
                        " \n" +
                        "6. Remove from skillet onto a WARM plate, cover loosely with foil and set aside for 5 minutes.\n" +
                        " \n" +
                        "7. Serve! I couldn't resist garlic butter - see recipe in notes.");
        Recipe chickenLiverWrappedInBacon = makeRecipe(
                "Chicken Liver Wrapped In Bacon",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas at odio nec sem ultrices cursus.",
                "https://cdn.pixabay.com/photo/2019/04/20/12/08/chicken-liver-4141673_1280.jpg",
                "halve pound bacon\n" +
                        "1 pound fresh chicken livers\n" +
                        "4 tablespoons honey\n",
                yvonne,
                "Step 1\n" +
                        "Set an oven rack about 6 inches from the heat source and preheat the oven's broiler.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Cut bacon slices in half; wrap bacon around each chicken liver, securing with toothpicks. \n" +
                        "Place on a broiler pan.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Broil in the preheated oven until bacon is cooked and crisp \n" +
                        "and livers are cooked through, 12 to 16 minutes, flipping halfway through. \n" +
                        "An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C).\n" +
                        "\n" +
                        "Step 4\n" +
                        "Serve with honey in a small dipping bowl.");
        Recipe fruitSaladWithYoghurt = makeRecipe(
                "Fruit Salad With Yoghurt",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas at odio nec sem ultrices cursus.",
                "https://cdn.pixabay.com/photo/2019/01/12/16/21/breakfast-3928800_1280.jpg",
                "2 cups strawberries (sliced)\n" +
                        "1 cup blueberries\n" +
                        "1 cup pineapple chuncks\n" +
                        "3 tablespoons pineapple juice\n" +
                        "2 cups plain yoghurt\n" +
                        "1/8 cup almonds (sliced or slivered)\n",
                jantje,
                "1. Place fruit in a large bowl and mix with pineapple juice. \n" +
                        "Let stand for 15 minutes at room temperature.\n" +
                        " \n" +
                        "2. Place 1 cup of fruit salad in a small bowl and top with 1/2 cup of yogurt.\n" +
                        " \n" +
                        "3. Sprinkle almonds on top of each fruit salad. Serve immediately.");
        Recipe bestCheesecakeEver = makeRecipe(
                "Best Cheesecake Ever",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas at odio nec sem ultrices cursus.",
                "https://cdn.pixabay.com/photo/2019/07/21/19/52/cheesecake-4353495_1280.jpg",
                "Graham cracker crust:\n" +
                        "1,5 cups graham cracker crumbs (170g)\n" +
                        "2 tablespoons sugar\n" +
                        "1 tablespoon brown sugar\n" +
                        "7 tablespoons butter melted\n" +
                        " \n" +
                        "cheesecake:\n" +
                        "32 oz cream cheese (softened to room temperature (910g)\n" +
                        "1 cup sugar (200g)\n" +
                        "2/3 cups sour cream (160g)\n" +
                        "1,5 teaspoons vanille extraxt\n" +
                        "1/8 teaspoon salt\n" +
                        "4 large eggs room temperature (lightly beaten\n",
                pietje,
                "1. Preheat over to 325F (1660C)\n" +
                        " \n" +
                        "2. Prepare Graham Cracker crust first by combining graham cracker crumbs, sugar and brown sugar, and stirring well. \n" +
                        "Add melted butter and use a fork to combine ingredients well.\n" +
                        "1 ½ cups graham cracker crumbs,2 Tablespoons sugar,\n" +
                        "1 Tablespoon brown sugar,7 Tablespoons butter\n" +
                        " \n" +
                        "3. Pour crumbs into a 9” Springform pan and press firmly into the bottom and up the sides of your pan. Set aside.\n" +
                        " \n" +
                        "Cheesecake\n" +
                        "1. In the bowl of a stand mixer or in a large bowl (using a hand mixer) \n" +
                        "add cream cheese and stir until smooth and creamy (don’t over-beat or you’ll incorporate too much air).\n" +
                        "32 oz cream cheese²\n" +
                        " \n" +
                        "2. Add sugar and stir again until creamy.\n" +
                        "1 cup sugar\n" +
                        " \n" +
                        "3. Add sour cream, vanilla extract, and salt, and stir until well-combined. \n" +
                        "If using a stand mixer, make sure you pause periodically to scrape the sides \n" +
                        "and bottom of the bowl with a spatula so that all ingredients are evenly incorporated.\n" +
                        "⅔ cups sour cream,1 ½ teaspoons vanilla extract,⅛ teaspoon salt\n" +
                                " \n" +
                        "4. With mixer on low speed, gradually add lightly beaten eggs, one at a time, stirring just until each egg is just incorporated. \n" +
                        "Once all eggs have been added, use a spatula to scrape the sides and bottom of the bowl again and make sure all ingredients are well combined.\n" +
                        "4 large eggs\n" +
                                "\n" +
                        "5. Pour cheesecake batter into prepared springform pan. To insure against leaks, place pan on a cookie sheet that’s been lined with foil.\n" +
                                "\n" +
                        "6. Transfer to the center rack of your oven and bake on 325F (160C) for 50-60 minutes (or longer as needed, see note 3). \n" +
                        "Edges will likely have slightly puffed and may have just begun to turn a light golden brown and the center should spring \n" +
                        "back to the touch but will still be Jello-jiggly. Don't over-bake or the texture will suffer, which means we all suffer.\n" +
                                "\n" +
                        "7. Remove from oven and allow to cool on top of the oven⁴ for 10 minutes. Once 10 minutes has passed, use a knife to gently \n" +
                        "loosen the crust from the inside of the springform pan (this will help prevent cracks as your cheesecake cools and shrinks). \n" +
                        "Do not remove the ring of the springform pan.\n" +
                                " \n" +
                        "8. Allow cheesecake to cool another 1-2 hours or until near room temperature before transferring to refrigerator and allowing \n" +
                        "to cool overnight or at least 6 hours. I remove the ring of the springform pan just before serving then return it to the pan to store. \n" +
                                " \n" +
                        "Enjoy!");
    }

    private Recipe makeRecipe(String recipeTitle,
                              String recipeDescription,
                              String imageUrl,
                              String ingredients,
                              ByteSizeUser recipeCreator,
                              String instructions) {
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
