package br.ifsc.edu.fln.logixpraias.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping
public class LoginController {
    @GetMapping
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @GetMapping("/forgot-password")
    public ModelAndView forgotPassword() {
        ModelAndView mv = new ModelAndView("password");
        return mv;
    }
}
