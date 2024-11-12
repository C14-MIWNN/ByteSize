package nl.miwnn.se14.bytesize.controller;

import nl.miwnn.se14.bytesize.service.ByteSizeUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

        return "userOverview";
    }

}
