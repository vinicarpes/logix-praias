package br.ifsc.edu.fln.logixpraias.controller;

import br.ifsc.edu.fln.logixpraias.model.*;
import br.ifsc.edu.fln.logixpraias.repository.CategoriaRepository;
import br.ifsc.edu.fln.logixpraias.repository.MaterialRepository;
import br.ifsc.edu.fln.logixpraias.repository.MaterialWithdrawalRepository;
import br.ifsc.edu.fln.logixpraias.repository.UsuarioRepository;
import br.ifsc.edu.fln.logixpraias.services.EmailService;
import br.ifsc.edu.fln.logixpraias.services.RecebimentoService;
import br.ifsc.edu.fln.logixpraias.services.RetiradaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/material/withdrawal")
public class MaterialWithdrawalController {
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    RetiradaService retiradaService;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    MaterialWithdrawalRepository retiradaRepository;
    @Autowired
    EmailService emailService;

    @GetMapping("")
    public ModelAndView home(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "10") int size) {

        List<Material> materiais = materialRepository.findAll();

        Pageable pageable = PageRequest.of(page, size);
        Page<RetiradaMaterial> recebimentoPages = retiradaRepository.findAll(pageable);

        int totalPages = recebimentoPages.getTotalPages();
        if (totalPages <= 0) {
            totalPages = 1;
        }

        List<Usuario> usuarios = usuarioRepository.findAll();

        ModelAndView mv = new ModelAndView("material-withdrawal");
        mv.addObject("totalPages",totalPages);
        mv.addObject("currentPage", page);
        mv.addObject("materialWithdrawal", new RetiradaMaterial());
        mv.addObject("retiradas", retiradaRepository.findAll());
        mv.addObject("categorias", categoriaRepository.findAll());
        mv.addObject("materiais", materiais);
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    @PostMapping
    public ModelAndView register(@ModelAttribute RetiradaMaterial retirada){
        try{
            retiradaService.save(retirada);
            emailService.sendEmail(emailService.toEmail(retirada));
            return new ModelAndView("redirect:/material/withdrawal");
        }catch(Exception e){
            e.printStackTrace();
            return new ModelAndView("500");
        }
    }

//    @PutMapping("/update/{id}")
//    @ResponseBody
//    public ModelAndView update(@ModelAttribute RetiradaMaterial retirada, @PathVariable Long id) throws SQLException{
//        Optional<RetiradaMaterial> optional = retiradaRepository.findById(id);
//        if(optional.isPresent()){
//            BeanUtils.copyProperties(retirada, optional.get(),"id");
//            retiradaRepository.save(optional.get());
//            return new ModelAndView("redirect:/material/withdrawal");
//        }
//        return new ModelAndView("500");
//    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody RetiradaMaterial retirada) {
        if (retiradaRepository.existsById(id)) {
            retirada.setId(id);
            retiradaRepository.save(retirada);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<RetiradaMaterial> get(@PathVariable Long id) {
        Optional<RetiradaMaterial> retirada = retiradaRepository.findById(id);
        return retirada.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
