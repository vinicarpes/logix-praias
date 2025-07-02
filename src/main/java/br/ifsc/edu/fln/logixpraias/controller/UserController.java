package br.ifsc.edu.fln.logixpraias.controller;

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

    /**
     * Tela de cadastro e listagem de usuários
     */
    @GetMapping("/register")
    public ModelAndView create(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuario", new Usuario());
        return new ModelAndView("user-register");
    }

    /**
     * Cadastro de novo usuário via formulário
     */
    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute Usuario usuario) {
        try {
            usuarioRepository.save(usuario);
            return new ModelAndView("redirect:/user/register");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("500");
        }
    }

    /**
     * Atualização de usuário via JSON (fetch PUT)
     */
    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isPresent()) {
            usuario.setId(id);
            usuarioRepository.save(usuario);
            return ResponseEntity.ok("Usuário atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<Usuario> get(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Deleta um usuário por ID
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if (optional.isPresent()) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
