package nl.miwnn.se14.bytesize.controller;

import jakarta.validation.Valid;
import nl.miwnn.se14.bytesize.dto.ByteSizeUserDTO;
import nl.miwnn.se14.bytesize.model.ByteSizeUser;
import nl.miwnn.se14.bytesize.service.ByteSizeUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Heron
 * Purpose for the class
 */

@Controller
@RequestMapping("/user")
public class ByteSizeUserController {
    private final ByteSizeUserService byteSizeUserService;

    public ByteSizeUserController(ByteSizeUserService byteSizeUserService) {
        this.byteSizeUserService = byteSizeUserService;
    }

    @GetMapping("/overview")
    public String showUserOverview(Model datamodel) {
        datamodel.addAttribute("allUsers", byteSizeUserService.getAllUsers());
        datamodel.addAttribute("formUser", new ByteSizeUserDTO());
        datamodel.addAttribute("formModalHidden", true);

        return "userOverview";
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

        byteSizeUserService.save(userDtoToBeSaved);
        return "redirect:/user/overview";
    }

}
