package com.nateebling.soloproject.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nateebling.soloproject.models.LoginUser;
import com.nateebling.soloproject.models.User;
import com.nateebling.soloproject.services.UserService;

@Controller
public class LoginController {
    
   @Autowired
   private UserService userService;
   

//    Get Requests
    @GetMapping("/")
    public String loginRegPage(Model model) {
    
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "loginreg.jsp";
    }
    
    
    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
    	session.setAttribute("id", null);
    	return "redirect:/";
    }
    
    // Post Requests
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        
        User registeredUser = userService.registerNewUser(newUser, result);
        
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "loginreg.jsp";
        }
        
        session.setAttribute("id", registeredUser.getId());
    
        return "redirect:/home";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
         User user = userService.loginExistingUser(newLogin, result);
    
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "loginreg.jsp";
        }
    
        session.setAttribute("id", user.getId());
    
        return "redirect:/home";
    }
    
}