package com.soumya.springbootFirst.controlllers;

import com.soumya.springbootFirst.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    /*@ResponseBody*/
    public String welcomepage(ModelMap model) {
        model.put("name", getLoggedinUserName());
        return "welcome";
    }

    public String getLoggedinUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();
        return principal.toString();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String sayWelcome(@RequestParam String name, @RequestParam String password, ModelMap model) {
       /* if (!loginService.isValidUser(name, password)) {
            model.put("errorMessage", "Invalid Credentials");
            return "login";
        }*/
        model.put("name", "soumya");
        return "redirect:welcome";
    }
}
