package br.ifsc.edu.fln.logixpraias.controller;

import br.ifsc.edu.fln.logixpraias.model.*;
import br.ifsc.edu.fln.logixpraias.repository.CategoriaRepository;
import br.ifsc.edu.fln.logixpraias.repository.MaterialRepository;
import br.ifsc.edu.fln.logixpraias.repository.UsuarioRepository;
import br.ifsc.edu.fln.logixpraias.services.RecebimentoService;
import br.ifsc.edu.fln.logixpraias.services.RetiradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @GetMapping("")
    public ModelAndView home() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<Material> materiais = materialRepository.findAll();
        List<Usuario> usuarios = usuarioRepository.findAll();
        ModelAndView mv = new ModelAndView("material-withdrawal");
        mv.addObject("materialWithdrawal", new RetiradaMaterial());
        mv.addObject("categorias", categorias);
        mv.addObject("materiais", materiais);
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    @PostMapping
    public ModelAndView register(@ModelAttribute RetiradaMaterial retirada){
        try{
            retiradaService.save(retirada);
            return new ModelAndView("redirect:/material/withdrawal");
        }catch(Exception e){
            e.printStackTrace();
            return new ModelAndView("500");
        }
    }
}
