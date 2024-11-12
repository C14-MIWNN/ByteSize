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
        datamodel.addAttribute("newRecipe", new Recipe());

        return "recipeForm";
    }

    @GetMapping("/recipe/new")
    private String showRecipeForm(Model datamodel) {
        datamodel.addAttribute("newRecipe", new Recipe());

        return "recipeForm";
    }

    @PostMapping("/recipe/new")
    private String saveOrUpdateRecipe(@ModelAttribute("newRecipe") Recipe recipeToBeSaved, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
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
