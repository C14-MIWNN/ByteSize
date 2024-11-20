package nl.miwnn.se14.bytesize.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Heron
 * Handles requests related to the landingpage
 */

@Controller
public class LandingpageController {

    @GetMapping("/")
    private String LandingpageController(Model datamodel) {
        return "landingpage";
    }
}
