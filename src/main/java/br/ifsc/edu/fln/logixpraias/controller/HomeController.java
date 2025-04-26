package br.ifsc.edu.fln.logixpraias.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        // qualquer atributo que queira passar
        return "index";   // Retorna o template “index.html” em /templates
    }

}
