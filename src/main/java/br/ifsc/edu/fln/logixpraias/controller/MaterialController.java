package br.ifsc.edu.fln.logixpraias.controller;

import br.ifsc.edu.fln.logixpraias.model.Categoria;
import br.ifsc.edu.fln.logixpraias.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/register")
    public ModelAndView create(Model model) {
        List<Categoria> categorias = categoriaRepository.findAll();
        model.addAttribute("categorias", categorias);
        ModelAndView mv = new ModelAndView("material-register");
        return mv;
    }

    @GetMapping("/update/{id}")
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
