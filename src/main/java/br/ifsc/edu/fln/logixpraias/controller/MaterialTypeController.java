package br.ifsc.edu.fln.logixpraias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/material/type")
public class MaterialTypeController {
    @PostMapping("/register")
    public ModelAndView register(){
        ModelAndView mv = new ModelAndView("material-type-register");
        return mv;
    }

    @PostMapping("/update/{id}")
    public void update(@PathVariable(value = "id") long id){

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") long id) {
        return "delete";
    }

    @GetMapping("/show/{id}")
    public String get(@PathVariable(value = "id") long id) {
        return "retorna show/{id}";
    }
}
