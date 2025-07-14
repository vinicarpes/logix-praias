package br.ifsc.edu.fln.logixpraias.controller;

import br.ifsc.edu.fln.logixpraias.model.*;
import br.ifsc.edu.fln.logixpraias.repository.CategoriaRepository;
import br.ifsc.edu.fln.logixpraias.repository.MaterialReceiptRepository;
import br.ifsc.edu.fln.logixpraias.repository.MaterialRepository;
import br.ifsc.edu.fln.logixpraias.repository.UsuarioRepository;
import br.ifsc.edu.fln.logixpraias.services.EmailService;
import br.ifsc.edu.fln.logixpraias.services.EstoqueService;
import br.ifsc.edu.fln.logixpraias.services.RecebimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/material/receipt")
public class MaterialReceiptController {

    @Autowired
    EmailService emailService;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    RecebimentoService  recebimentoService;
    @Autowired
    MaterialReceiptRepository recebimentoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("")
    public ModelAndView home(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "10") int size) {

        List<Categoria> categorias = categoriaRepository.findAll();
        List<Material> materiais = materialRepository.findAll();

        Pageable pageable = PageRequest.of(page, size);
        Page<RecebimentoMaterial> recebimentoPages = recebimentoRepository.findAll(pageable);

        int totalPages = recebimentoPages.getTotalPages();
        if (totalPages <= 0) {
            totalPages = 1;
        }

        ModelAndView mv = new ModelAndView("material-receipt");
        mv.addObject("totalPages",totalPages);
        mv.addObject("currentPage", page);
        mv.addObject("materialReceipt", new RecebimentoMaterial());
        mv.addObject("categorias", categorias);
        mv.addObject("materiais", materiais);
        mv.addObject("recebimentos", recebimentoRepository.findAll());
        mv.addObject("usuarios", usuarioRepository.findAll());
        return mv;
    }

    @PostMapping
    public ModelAndView register(@ModelAttribute RecebimentoMaterial recebimento){
        try{
            recebimentoService.save(recebimento);
            return new ModelAndView("redirect:/material/receipt");
        }catch(Exception e){
            e.printStackTrace();
            return new ModelAndView("500");
        }
    }
    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody RecebimentoMaterial recebimento) {
        if (recebimentoRepository.existsById(id)) {
            recebimento.setId(id);
            recebimentoRepository.save(recebimento);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<RecebimentoMaterial> get(@PathVariable Long id) {
        Optional<RecebimentoMaterial> recebimento = recebimentoRepository.findById(id);
        return recebimento.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

