package nl.miwnn.se14.bytesize.controller;

import jakarta.validation.Valid;
import nl.miwnn.se14.bytesize.dto.ByteSizeUserDTO;
import nl.miwnn.se14.bytesize.model.ByteSizeUser;
import nl.miwnn.se14.bytesize.repositories.ByteSizeUserRepository;
import nl.miwnn.se14.bytesize.repositories.RecipeRepository;
import nl.miwnn.se14.bytesize.service.ByteSizeUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Heron
 * Handles requests related to the user
 */

@Controller
@RequestMapping("/user")
public class ByteSizeUserController {
    private final ByteSizeUserService byteSizeUserService;
    private final ByteSizeUserRepository byteSizeUserRepository;
    private final RecipeRepository recipeRepository;

    public ByteSizeUserController(ByteSizeUserService byteSizeUserService,
                                  ByteSizeUserRepository byteSizeUserRepository, RecipeRepository recipeRepository) {
        this.byteSizeUserService = byteSizeUserService;
        this.byteSizeUserRepository = byteSizeUserRepository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/overview")
    public String showUserOverview(Model datamodel) {
        datamodel.addAttribute("allUsers", byteSizeUserService.getAllUsers());
        datamodel.addAttribute("formUser", new ByteSizeUserDTO());
        datamodel.addAttribute("formModalHidden", true);

        return "userOverview";
    }

    @GetMapping("/register")
    public String showUserRegister(Model datamodel) {
        datamodel.addAttribute("allUsers", byteSizeUserService.getAllUsers());
        datamodel.addAttribute("formUser", new ByteSizeUserDTO());
        datamodel.addAttribute("formModalHidden", false);

        return "userOverview";
    }

    @GetMapping("/details/{username}")
    public String showUserDetailPage(@PathVariable("username") String username, Model datamodel) {
        Optional<ByteSizeUser> byteSizeUser = byteSizeUserRepository.findByUsername(username);


        if (byteSizeUser.isEmpty()) {
            return "redirect:/overview";
        }

        datamodel.addAttribute("byteSizeUserToBeShown", byteSizeUser.get());
        datamodel.addAttribute("allRecipes", recipeRepository.findAll());

        return "userDetails";
    }

    @PostMapping("/save")
    private String saveOrUpdateUser(@ModelAttribute("formUser") @Valid ByteSizeUserDTO userDtoToBeSaved,
                                    BindingResult result, Model datamodel) {
        if (byteSizeUserService.usernameInUse(userDtoToBeSaved.getUsername())) {
            result.rejectValue("username", "duplicate", "This username is already in use");
        }

        if (!userDtoToBeSaved.getPassword().equals(userDtoToBeSaved.getPasswordConfirm())) {
            result.rejectValue("password", "no.match", "The passwords do not match");
        }

        if (result.hasErrors()) {
            datamodel.addAttribute("allUsers", byteSizeUserService.getAllUsers());
            return "userOverview";
        }

        userDtoToBeSaved.setRole("USER");

        byteSizeUserService.save(userDtoToBeSaved);
        return "redirect:/user/overview";
    }

    @GetMapping("/delete/{username}")
    private String deleteUser(@PathVariable("username") String username) {
        byteSizeUserRepository.findByUsername(username).ifPresent((byteSizeUserRepository::delete));
        return "redirect:/user/overview";
    }

}
