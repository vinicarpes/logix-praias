package br.ifsc.edu.fln.logixpraias.controller;

import br.ifsc.edu.fln.logixpraias.model.Categoria;
import br.ifsc.edu.fln.logixpraias.model.Material;
import br.ifsc.edu.fln.logixpraias.model.Usuario;
import br.ifsc.edu.fln.logixpraias.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/register")
    public ModelAndView create(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        System.out.println(usuarios);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuario", new Usuario());
        ModelAndView mv = new ModelAndView("user-register");
        return mv;
    }
//
    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute Usuario usuario) {
        try{
            usuarioRepository.save(usuario);
            return new ModelAndView("redirect:/material/register");
        }catch(Exception e){
            e.printStackTrace();
            return new ModelAndView("500");
        }
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);
            usuarioRepository.save(usuario);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<Usuario> get(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if(optional.isPresent()) {
            usuarioRepository.deleteById(id);
            return  ResponseEntity.ok().build();
        }
        return  ResponseEntity.notFound().build();
    }
}
