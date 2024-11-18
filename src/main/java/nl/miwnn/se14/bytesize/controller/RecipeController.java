package nl.miwnn.se14.bytesize.controller;

import nl.miwnn.se14.bytesize.model.Recipe;
import nl.miwnn.se14.bytesize.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * @author Yvonne.
 * Handle all request related to recipes.
 */

@Controller
public class RecipeController {
    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping({"/", "/recipe/overview"})
    private String showRecipeOverview(Model datamodel) {
        datamodel.addAttribute("allRecipes", recipeRepository.findAll());

        return "recipeOverview";
    }

    @GetMapping("/recipe/new")
    private String showRecipeForm(Model datamodel) {
        return setupRecipeForm(datamodel, new Recipe());
    }

    @GetMapping("/recipe/detail/{recipeTitle}")
    private String showRecipeDetailPage(@PathVariable("recipeTitle") String recipeTitle, Model datamodel) {
        Optional<Recipe> recipeOptional = recipeRepository.findByRecipeTitle(recipeTitle);

        if (recipeOptional.isEmpty()) {
            return "redirect:/recipe/overview";
        }

        datamodel.addAttribute("recipe", recipeOptional.get());
        return "recipeDetail";
    }

    @GetMapping("/recipe/edit/{recipeTitle}")
    private String showRecipeEditPage(@PathVariable("recipeTitle") String recipeTitle, Model datamodel) {
        Optional<Recipe> recipeOptional = recipeRepository.findByRecipeTitle(recipeTitle);

        if (recipeOptional.isEmpty()) {
            return "redirect:/recipe/overview";
        }

        return setupRecipeForm(datamodel, recipeOptional.get());
    }

    private String setupRecipeForm(Model datamodel, Recipe recipeOptional) {
        datamodel.addAttribute("formRecipe", recipeOptional);

        return "recipeForm";
    }

    @PostMapping("/recipe/new")
    private String saveOrUpdateRecipe(@ModelAttribute("formRecipe") Recipe recipeToBeSaved, BindingResult result) {
        if (result.hasErrors()) {
            System.err.println(result.getAllErrors());
            return "redirect:/recipe/overview";
        }

        recipeRepository.save(recipeToBeSaved);
        return "redirect:/recipe/overview";
    }

    @GetMapping("/recipe/delete/{recipeId}")
    private String deleteRecipe(@PathVariable("recipeId") Long recipeId) {
        recipeRepository.deleteById(recipeId);
        return "redirect:/recipe/overview";
    }
}
