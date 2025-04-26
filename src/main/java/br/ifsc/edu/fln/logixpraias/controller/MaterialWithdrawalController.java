package br.ifsc.edu.fln.logixpraias.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/material/withdrawal")
public class MaterialWithdrawalController {
    @GetMapping("")
    public ModelAndView withdrawal() {
        ModelAndView mv = new ModelAndView("material-withdrawal");
        return mv;
    }
    @GetMapping("/show")
    public ResponseEntity<String> show() {
        return ResponseEntity.ok("");
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<String> get(@PathVariable(value = "id") long id){
        return ResponseEntity.ok("");
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable(value = "id") long id) {
        ModelAndView mv = new ModelAndView("");
        return mv;
    }
}
