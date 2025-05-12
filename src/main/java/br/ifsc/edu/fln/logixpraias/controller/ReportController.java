package br.ifsc.edu.fln.logixpraias.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/reports")
public class ReportController {
    @GetMapping()
    public ModelAndView page() {
        ModelAndView mv = new ModelAndView("reports");
        return mv;
    }

    @GetMapping("/charts")
    public ModelAndView charts() {
        ModelAndView mv = new ModelAndView("charts");
        return mv;
    }

}
