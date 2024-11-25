package nl.miwnn.se14.bytesize.controller;

import nl.miwnn.se14.bytesize.model.ByteSizeUser;
import nl.miwnn.se14.bytesize.model.Recipe;
import nl.miwnn.se14.bytesize.repositories.ByteSizeUserRepository;
import nl.miwnn.se14.bytesize.repositories.RecipeRepository;
import nl.miwnn.se14.bytesize.service.ByteSizeUserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Yvonne.
 * Handle all request related to recipes.
 */

@Controller
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final ByteSizeUserRepository byteSizeUserRepository;

    public RecipeController(RecipeRepository recipeRepository, ByteSizeUserRepository byteSizeUserRepository) {
        this.recipeRepository = recipeRepository;
        this.byteSizeUserRepository = byteSizeUserRepository;
    }

    @GetMapping("/recipe/overview")
    private String showRecipeOverview(Model datamodel) {
        datamodel.addAttribute("allRecipes", recipeRepository.findAll());

        return "recipeOverview";
    }

    @GetMapping("/recipe/new")
    private String showRecipeForm(Model datamodel) {
        return setupRecipeForm(datamodel, new Recipe());
    }

    @GetMapping("/recipe/edit/{recipeId}")
    private String showRecipeEditPage(@PathVariable("recipeId") Long recipeId, Model datamodel) {
        Optional<Recipe> recipeOptional = recipeRepository.findByRecipeId(recipeId);

        if (recipeOptional.isEmpty()) {
            return "redirect:/recipe/detail";
        }

        return setupRecipeForm(datamodel, recipeOptional.get());
    }

    @GetMapping("/recipe/detail/{recipeId}")
    private String showRecipeDetailPage(@PathVariable("recipeId") Long recipeId, Model datamodel) {
        Optional<Recipe> recipeOptional = recipeRepository.findByRecipeId(recipeId);

        if (recipeOptional.isEmpty()) {
            return "redirect:/recipe/overview";
        }

        datamodel.addAttribute("recipe", recipeOptional.get());
        return "recipeDetail";
    }

    private String setupRecipeForm(Model datamodel, Recipe recipeOptional) {
        datamodel.addAttribute("formRecipe", recipeOptional);
        datamodel.addAttribute("searchForm", new Recipe());

        return "recipeForm";
    }

    @PostMapping("/recipe/new")
    private String saveOrUpdateRecipe(@ModelAttribute("formRecipe") Recipe recipeToBeSaved, BindingResult result) {
        if (result.hasErrors()) {
            System.err.println(result.getAllErrors());
            return "redirect:/recipe/overview";
        }

        ByteSizeUser user = findByteSizeUserByUsername();
        recipeToBeSaved.setRecipeCreator(user);

        recipeRepository.save(recipeToBeSaved);
        return "redirect:/recipe/overview";
    }

    private ByteSizeUser findByteSizeUserByUsername() {
        String username = ByteSizeUserService.getUsername();
        Optional<ByteSizeUser> userOptional = byteSizeUserRepository.findByUsername(username);
        return userOptional.orElseThrow(
            () -> new UsernameNotFoundException(
                String.format("Username '%s' was not found in the database", username)));
    }

    @GetMapping("/recipe/delete/{recipeId}")
    private String deleteRecipe(@PathVariable("recipeId") Long recipeId) {
        recipeRepository.findByRecipeId(recipeId).ifPresent((recipeRepository::delete));
        return "redirect:/recipe/overview";
    }

}