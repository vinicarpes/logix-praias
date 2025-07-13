package br.ifsc.edu.fln.logixpraias.controller;

import br.ifsc.edu.fln.logixpraias.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import br.ifsc.edu.fln.logixpraias.model.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/material/type")
public class MaterialTypeController {
    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping("/register")
    public ModelAndView home(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Categoria> categoriaPage = categoriaRepository.findAll(pageable);

        int totalPages = categoriaPage.getTotalPages();
        if (totalPages <= 0) totalPages = 1;

        ModelAndView mv = new ModelAndView("material-type-register");
        mv.addObject("categoria", new Categoria());
        mv.addObject("categorias", categoriaPage.getContent());
        mv.addObject("totalPages", totalPages);
        mv.addObject("currentPage", page);

        return mv;
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

    @PutMapping("/update/{id}")
    @ResponseBody
    public String update(@PathVariable Long id, @RequestBody Categoria categoria) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);

        if (optionalCategoria.isPresent()) {
            Categoria cat = optionalCategoria.get();
            cat.setNome(categoria.getNome());
            cat.setDescricao(categoria.getDescricao());
            categoriaRepository.save(cat);
            return "redirect:/material/type/register";
        }
        return "redirect:/500";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Categoria> get(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/show/{id}")
    public String get(@PathVariable(value = "id") long id) {
        return "retorna show/{id}";
    }
}
