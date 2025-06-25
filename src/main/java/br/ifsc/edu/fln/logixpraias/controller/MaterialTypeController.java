package br.ifsc.edu.fln.logixpraias.controller;

import br.ifsc.edu.fln.logixpraias.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import br.ifsc.edu.fln.logixpraias.model.*;

@Controller
@RequestMapping("/material/type")
public class MaterialTypeController {
    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping("/register")
    public String create(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "material-type-register"; // nome do HTML (sem extensão)
    }

    @PostMapping("/register")
    public String save(@ModelAttribute Categoria categoria) {
        try {
            categoriaRepository.save(categoria);
            return "redirect:/index";
        }catch(Exception e) {
            e.printStackTrace();
            return "redirect:/material/type/register";
        }
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
