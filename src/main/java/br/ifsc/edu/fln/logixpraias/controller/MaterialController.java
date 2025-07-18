package br.ifsc.edu.fln.logixpraias.controller;

import br.ifsc.edu.fln.logixpraias.model.Categoria;
import br.ifsc.edu.fln.logixpraias.model.Material;
import br.ifsc.edu.fln.logixpraias.repository.CategoriaRepository;
import br.ifsc.edu.fln.logixpraias.repository.MaterialRepository;
import br.ifsc.edu.fln.logixpraias.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ModelAndView get(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size) {

        List<Categoria> categorias = categoriaRepository.findAll();

        // Corrigindo: página começa do zero no Spring
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Material> materialPage = materialRepository.findAll(pageable);

        int totalPages = materialPage.getTotalPages();
        if (totalPages <= 0) totalPages = 1;

        ModelAndView mv = new ModelAndView("material-register");
        mv.addObject("categorias", categorias);
        mv.addObject("materiais", materialPage.getContent());
        mv.addObject("material", new Material());
        mv.addObject("totalPages", totalPages);
        mv.addObject("currentPage", page);

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

    @GetMapping("/by-category")
    @ResponseBody
    public List<Material> getMaterialsByCategory(@RequestParam Long categoriaId) {
        return materialRepository.findByCategoriaId(categoriaId);
    }
}
