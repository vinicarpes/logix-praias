package br.ifsc.edu.fln.logixpraias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/material/receive")
public class MaterialReceiptController {
    @GetMapping("")
    public ModelAndView receive() {
        ModelAndView mv = new ModelAndView("material-receipt");
        return mv;
    }

    @GetMapping("/show")
    public ModelAndView show(){
        ModelAndView mv = new ModelAndView("show-storage");
        return mv;
    }
    
    @GetMapping("/show/{id}")
    public ModelAndView show(@PathVariable(value = "id") long id){
        ModelAndView mv = new ModelAndView("");
        return mv;
    }
}
