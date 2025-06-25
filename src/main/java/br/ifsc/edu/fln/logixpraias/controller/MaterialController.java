package br.ifsc.edu.fln.logixpraias.controller;

import br.ifsc.edu.fln.logixpraias.model.Categoria;
import br.ifsc.edu.fln.logixpraias.model.Material;
import br.ifsc.edu.fln.logixpraias.repository.CategoriaRepository;
import br.ifsc.edu.fln.logixpraias.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private MaterialRepository materialRepository;

    @GetMapping("/register")
    public ModelAndView create(Model model) {
        List<Categoria> categorias = categoriaRepository.findAll();
        model.addAttribute("materiais", materialRepository.findAll());
        model.addAttribute("categorias", categorias);
        model.addAttribute("material",  new Material());
        ModelAndView mv = new ModelAndView("material-register");
        return mv;
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute Material material) {
        try{
            materialRepository.save(material);
            return new ModelAndView("redirect:/index");
        }catch(Exception e){
            e.printStackTrace();
            return new ModelAndView("500");
        }
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
