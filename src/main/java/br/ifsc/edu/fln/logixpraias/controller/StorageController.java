package br.ifsc.edu.fln.logixpraias.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/show")
    public ModelAndView show() {
        ModelAndView mv = new ModelAndView("show-storage");
        return mv;
    }

    @GetMapping("/get")
    public ModelAndView buscarEstoque(
            @RequestParam(required = false) Long material,
            @RequestParam(required = false) Long categoria,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(name = "quantity-param", required = false) String op) {

        Specification<Estoque> spec = Specification.where(null);

        spec = spec.and(EstoqueSpecification.temMaterial(material));
        spec = spec.and(EstoqueSpecification.temCategoria(categoria));
        spec = spec.and(EstoqueSpecification.quantidade(op, quantity));

        List<Estoque> resultados = estoqueRepository.findAll(spec);

        ModelAndView mv = new ModelAndView("show-storage");
        mv.addObject("estoques", resultados);
        return mv;
    }

}
