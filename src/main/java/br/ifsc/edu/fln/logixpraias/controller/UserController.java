package br.ifsc.edu.fln.logixpraias.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/register")
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("user-register");
        return mv;
    }

    @GetMapping("")
    public ModelAndView show() {
        ModelAndView mv = new ModelAndView("show-users");
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable(value = "id") long id) {
        ModelAndView mv = new ModelAndView("user");
        mv.addObject("id", id);
        //talvez adicionar o log do usuario aqui
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable(value = "id") long id) {
        ModelAndView mv = new ModelAndView("user");
        mv.addObject("id", id);
        return mv;
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable(value = "id") long id) {
        ModelAndView mv = new ModelAndView("user");
        mv.addObject("id", id);
        return mv;
    }

    @GetMapping("/log")
    public ModelAndView log() {
        ModelAndView mv = new ModelAndView("users-log");
        return mv;
    }
}
