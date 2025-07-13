package br.ifsc.edu.fln.logixpraias.controller;

import br.ifsc.edu.fln.logixpraias.model.Categoria;
import br.ifsc.edu.fln.logixpraias.model.Material;
import br.ifsc.edu.fln.logixpraias.repository.CategoriaRepository;
import br.ifsc.edu.fln.logixpraias.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import br.ifsc.edu.fln.logixpraias.model.Estoque;
import br.ifsc.edu.fln.logixpraias.repository.EstoqueRepository;
import br.ifsc.edu.fln.logixpraias.repository.EstoqueSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@RestController
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private MaterialRepository materialRepository;

    @GetMapping("/get")
    public ModelAndView buscarEstoque(
            @RequestParam(required = false) Long material,
            @RequestParam(required = false) Long categoria,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(name = "quantity-param", required = false) String op,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Specification<Estoque> spec = Specification.where(null);

        spec = spec.and(EstoqueSpecification.temMaterial(material));
        spec = spec.and(EstoqueSpecification.temCategoria(categoria));
        spec = spec.and(EstoqueSpecification.quantidade(op, quantity));

        Pageable pageable = PageRequest.of(page, size);
        Page<Estoque> estoquesPage = estoqueRepository.findAll(spec, pageable);

        int totalPages = estoquesPage.getTotalPages();
        if (totalPages <= 0) {
            totalPages = 1;
        }

        List<Categoria> categorias = categoriaRepository.findAll();
        List<Material> materiais = materialRepository.findAll();

        ModelAndView mv = new ModelAndView("show-storage");
        mv.addObject("estoques", estoquesPage.getContent());
        mv.addObject("totalPages", totalPages);
        mv.addObject("currentPage", page);
        mv.addObject("categorias", categorias);
        mv.addObject("materiais", materiais);

        // Repassa filtros atuais para manter na navegação
        mv.addObject("filtroMaterial", material);
        mv.addObject("filtroCategoria", categoria);
        mv.addObject("filtroQuantity", quantity);
        mv.addObject("filtroOp", op);

        return mv;
    }
}
