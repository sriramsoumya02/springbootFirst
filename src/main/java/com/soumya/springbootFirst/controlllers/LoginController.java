package com.soumya.springbootFirst.controlllers;

import com.soumya.springbootFirst.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    /*@ResponseBody*/
    public String loginpage(ModelMap model) {
        model.put("name", "soumya");
        return "redirect:welcome";
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
