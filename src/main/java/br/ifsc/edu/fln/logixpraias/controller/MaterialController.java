package br.ifsc.edu.fln.logixpraias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/material")
public class MaterialController {

    @PostMapping("/register")
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("material-register");
        return mv;
    }

    @PostMapping("/update/{id}")
    public void update(@PathVariable(value = "id") long id) {

    }

    @GetMapping("/show/{id}")
    public void get(@PathVariable("id") long id) {
//        return "www.url.com/material/get/" + id;
    }

    @GetMapping("/show")
    public ModelAndView show() {
        ModelAndView mv = new ModelAndView("show-storage");
        return mv;
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable(value = "id") long id) {

    }
}
