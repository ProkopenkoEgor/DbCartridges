package com.example.springbootsbyt.controller;

import com.example.springbootsbyt.model.Cartrs;
import com.example.springbootsbyt.model.Printers;
import com.example.springbootsbyt.service.impl.CartrsServiceImpl;
import com.example.springbootsbyt.service.impl.PrintersServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PrintersController {
    private final PrintersServiceImpl printersServiceImpl;
    private final CartrsServiceImpl cartrsServiceImpl;

    public PrintersController(PrintersServiceImpl printersServiceImpl, CartrsServiceImpl cartrsServiceImpl) {
        this.printersServiceImpl = printersServiceImpl;
        this.cartrsServiceImpl = cartrsServiceImpl;
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
    public String createPrinters(Printers printers, BindingResult bindingResult) {
        Printers printer = null;
        List<Printers> printers1 = printersServiceImpl.findAll();
        String str = printers.getTypePrinters();
        for(int i=0; i<printers1.size(); i++){
            printer=printers1.get(i);
            if(str.equalsIgnoreCase(printer.getTypePrinters())==true){
                bindingResult.rejectValue("typePrinters","error.typePrinters","Такой тип принтера уже есть");
                return "/printers-create";
            }
        }

        printersServiceImpl.savePrinters(printers);
        return "redirect:/printers";
    }
    @GetMapping("/printers-update/{id}")
    public String updatePrintersForm(@PathVariable("id") Integer id, Model model){
        Printers printers = printersServiceImpl.findById(id);
        Cartrs cartrs=cartrsServiceImpl.findById(id);
        model.addAttribute("printers",printers);
        model.addAttribute("cartrs",cartrs);
        return "printers-update";
    }

    @PostMapping("/printers-update")
    public String updatePrinters(Printers printers){
        printersServiceImpl.savePrinters(printers);
        return"redirect:/printers";
    }

    @GetMapping("/printers-delete/{id}")
    public String deletePrinters(@PathVariable("id") Integer id) {
        printersServiceImpl.deleteById(id);
        return "redirect:/printers";
    }
}
