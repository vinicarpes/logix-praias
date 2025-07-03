package br.ifsc.edu.fln.logixpraias.controller;

import br.ifsc.edu.fln.logixpraias.model.Categoria;
import br.ifsc.edu.fln.logixpraias.model.Material;
import br.ifsc.edu.fln.logixpraias.repository.CategoriaRepository;
import br.ifsc.edu.fln.logixpraias.repository.MaterialRepository;
import br.ifsc.edu.fln.logixpraias.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private EstoqueRepository estoqueRepository;

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
            material.getEstoque().setMaterial(material);
            materialRepository.save(material);
            return new ModelAndView("redirect:/material/register");
        }catch(Exception e){
            e.printStackTrace();
            return new ModelAndView("500");
        }
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Material material) {
        if (materialRepository.existsById(id)) {
            material.setId(id);
            materialRepository.save(material);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<Material> get(@PathVariable Long id) {
        Optional<Material> material = materialRepository.findById(id);
        return material.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/show")
    public ModelAndView show() {
        ModelAndView mv = new ModelAndView("show-storage");
        return mv;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        Optional<Material> optionalMaterial = materialRepository.findById(id);
        if(optionalMaterial.isPresent()) {
            materialRepository.deleteById(id);
            return  ResponseEntity.ok().build();
        }
        return  ResponseEntity.notFound().build();
    }
}
