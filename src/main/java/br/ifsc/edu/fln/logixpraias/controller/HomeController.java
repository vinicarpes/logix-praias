package br.ifsc.edu.fln.logixpraias.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/index")
public class HomeController {
    @GetMapping()
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("index");
        return mv;   // Retorna o template “index.html” em /templates
    }

    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView mv = new ModelAndView("about");
        return mv;
    }
}
