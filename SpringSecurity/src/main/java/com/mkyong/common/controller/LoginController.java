package com.mkyong.common.controller;

import java.security.Principal;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.scribe.ScribeAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, Principal principal) {
        System.out.println("Welcome plain callled ");
        String name = principal.getName();
        model.addAttribute("username", name);
        model.addAttribute("message", "Spring Security Custom Form example");
        return "hello";

    }

    @RequestMapping(value = "/welcomeOAuth", method = RequestMethod.GET)
    public String welcomeOAuth(ModelMap model, Principal principal) {
        System.out.println("Welcome callled ");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username

//        String name = principal.getName();
        model.addAttribute("username", name);
        model.addAttribute("message", "welcomeOAuth");
        return "hello";

    }

    @RequestMapping(value = "/facebook-callback", method = RequestMethod.GET)
    public String facebookCallback(ModelMap model, Principal principal) {
        System.out.println("Callback callled ");
//        String name = principal.getName();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        System.out.println("name " + name);
        ScribeAuthentication scauth = (ScribeAuthentication) auth;
        Map<String, Object> scribeDetails = scauth.getScribeDetails();
        for (Map.Entry<String, Object> entry : scribeDetails.entrySet()) {
    System.out.println("key=" + entry.getKey() + ", value=" + entry.getValue());
}
        model.addAttribute("username", name);
        model.addAttribute("message", "FB Call back");
        return "hello";

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        System.out.println("Login callled ");
        return "login";

    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error(ModelMap model) {
        System.out.println("Error callled ");
        return "error";

    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        System.out.println("LoginFailed callled ");
        model.addAttribute("error", "true");
        return "login";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {

        return "login";

    }

}
