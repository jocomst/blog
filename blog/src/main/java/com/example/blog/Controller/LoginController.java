<<<<<<< HEAD
package com.example.blog.controller;
=======
package com.example.blog.Controller;
>>>>>>> 9631e3c5d39b2380a361072705344ce6b2006d01

import com.example.blog.Forms.LoginForm;
import com.example.blog.service.NotificationService;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.Column;

import javax.validation.Valid;

//import javax.validation.Valid;

@Controller
public class LoginController {

     @Autowired
     private UserService userService;

     @Autowired
     private NotificationService notifyService;

     @RequestMapping("users/login")
     public String login(LoginForm loginForm) {
         return "users/login";
     }

     @RequestMapping(value = "users/login", method = RequestMethod.POST)
     public String loginPage(@Validated LoginForm loginForm, BindingResult bindingResult) {
         if (bindingResult.hasErrors()) {
              notifyService.addErrorMessage("Please fill the form correctly!");
              return "users/login";
         }

         if (!userService.authenticate(
              loginForm.getUsername(), loginForm.getPassword())) {
              notifyService.addErrorMessage("Invalid login!");
              return "users/login";
         }

         notifyService.addInfoMessage("Login successful");
         return "redirect:/";
     }
 }
