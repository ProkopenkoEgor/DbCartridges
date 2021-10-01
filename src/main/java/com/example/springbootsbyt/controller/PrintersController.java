package com.example.springbootsbyt.controller;

import com.example.springbootsbyt.model.Printers;
import com.example.springbootsbyt.service.impl.PrintersServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PrintersController {
    private final PrintersServiceImpl printersServiceImpl;

    public PrintersController(PrintersServiceImpl printersServiceImpl) {
        this.printersServiceImpl = printersServiceImpl;
    }

    @GetMapping("/printers")
    public String findAllPrinters(Model model){
        List<Printers> printers = printersServiceImpl.findAll();
        model.addAttribute("printers", printers);
        return "printers-list";
    }

    @GetMapping("/printers-create")
    public String createPrintersForm(Printers printers) {
        return "printers-create";
    }

    @PostMapping("/printers-create")
    public String createPrinters(Printers printers) {
        printersServiceImpl.savePrinters(printers);
        return "redirect:/cartridges";
    }
    @GetMapping("/printers-update/{id}")
    public String updatePrintersForm(@PathVariable("id") Integer id, Model model){
        Printers printers = printersServiceImpl.findById(id);
        model.addAttribute("printers",printers);
        return "printers-update";
    }

    @PostMapping("/printers-update")
    public String updatePrinters(Printers printers){
        printersServiceImpl.savePrinters(printers);
        return"redirect:/cartridges";
    }

    @GetMapping("/printers-delete/{id}")
    public String deletePrinters(@PathVariable("id") Integer id) {
        printersServiceImpl.deleteById(id);
        return "redirect:/cartridges";
    }
}
