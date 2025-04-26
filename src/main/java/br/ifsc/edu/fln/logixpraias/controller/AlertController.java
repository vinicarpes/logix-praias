package br.ifsc.edu.fln.logixpraias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/alert")
public class AlertController {
    @GetMapping("/register")
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("alert-register");
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable(value = "id") long id){
        ModelAndView mv = new ModelAndView("alert-delete");
        return mv;
    }

    @GetMapping("/show/{id}")
    public ModelAndView show(@PathVariable(value = "id") long id){
        ModelAndView mv = new ModelAndView("alert-show");
        return mv;
    }

    @GetMapping("/show")
    public ModelAndView show(){
        ModelAndView mv = new ModelAndView("alert-show");
        return mv;
    }

}
