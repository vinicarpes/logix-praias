package br.ifsc.edu.fln.logixpraias.controller;

import br.ifsc.edu.fln.logixpraias.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "material-type-register"; // nome do HTML (sem extensão)
    }

    @PostMapping("/register")
    public String save(@ModelAttribute Categoria categoria) {
        try {
            categoriaRepository.save(categoria);
            return "redirect:/material/type/register";
        }catch(Exception e) {
            e.printStackTrace();
            return "redirect:/500";
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
        if(categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/show/{id}")
    public String get(@PathVariable(value = "id") long id) {
        return "retorna show/{id}";
    }
}
