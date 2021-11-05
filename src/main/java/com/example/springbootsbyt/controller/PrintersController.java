package com.example.springbootsbyt.controller;

import com.example.springbootsbyt.model.Manufacturers;
import com.example.springbootsbyt.model.Printers;
import com.example.springbootsbyt.service.impl.ManufacturerServiceImpl;
import com.example.springbootsbyt.service.impl.PrintersServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PrintersController {
    private final PrintersServiceImpl printersServiceImpl;
    private final ManufacturerServiceImpl manufacturerServiceImpl;

    public PrintersController(PrintersServiceImpl printersServiceImpl, ManufacturerServiceImpl manufacturerServiceImpl) {
        this.printersServiceImpl = printersServiceImpl;
        this.manufacturerServiceImpl = manufacturerServiceImpl;
    }

    @GetMapping("/printers")
    public String findAllPrinters(Model model){
        List<Printers> printers = printersServiceImpl.findAll();
        List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
        model.addAttribute("printers", printers);
        model.addAttribute("manufacturers", manufacturers);
        return "printers-list";
    }

    @GetMapping("/printers-create")
    public String createPrintersForm(Printers printers, Model model) {
        List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
        model.addAttribute("manufacturers",manufacturers);
        return "printers-create";
    }

    @PostMapping("/printers-create")
    public String createPrinters(@Valid Printers printers, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()){
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            model.addAttribute("manufacturers",manufacturers);
            return "printers-create";
        }
            Printers printer = null;
            List<Printers> printers1 = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            model.addAttribute("manufacturers",manufacturers);
            String str = printers.getTypePrinters();
            for (int i = 0; i < printers1.size(); i++) {
                printer = printers1.get(i);
                if (str.equalsIgnoreCase(printer.getTypePrinters()) == true) {
                    bindingResult.rejectValue("typePrinters", "error.typePrinters", "Такой тип принтера уже существует");
                    return "/printers-create";
                }
            }

        printersServiceImpl.savePrinters(printers);
        return "redirect:/printers";
    }
    @GetMapping("/printers-update/{idPrinters}")
    public String updatePrintersForm(@PathVariable("idPrinters") Integer id, Model model){
        Printers printers = printersServiceImpl.findById(id);
        List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
        model.addAttribute("printers",printers);
        model.addAttribute("manufacturers",manufacturers);
        return "printers-update";
    }

    @PostMapping("/printers-update/{idPrinters}")
    public String updatePrinters(@PathVariable("idPrinters") int idPrinters,@Valid Printers printers,BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            model.addAttribute("manufacturers",manufacturers);
            return "printers-update";
        }
        String str = printers.getTypePrinters();
        Printers printers2 = printersServiceImpl.findById(idPrinters);
        String str2 = printers2.getTypePrinters();
        if (str.equalsIgnoreCase(str2)) {
            printersServiceImpl.savePrinters(printers);
            return "redirect:/printers";
        }else {
            Printers printer = null;
            List<Printers> printers1 = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            model.addAttribute("manufacturers", manufacturers);
            String str1 = printers.getTypePrinters();
            for (int i = 0; i < printers1.size(); i++) {
                printer = printers1.get(i);
                if (str1.equalsIgnoreCase(printer.getTypePrinters()) == true) {
                    bindingResult.rejectValue("typePrinters", "error.typePrinters", "Така модель принтера уже существует");
                    return "printers-update";
                }
            }
        }
        printersServiceImpl.savePrinters(printers);
        return"redirect:/printers";
    }

    @GetMapping("/printers-delete/{id}")
    public String deletePrinters(@PathVariable("id") Integer id) {
        printersServiceImpl.deleteById(id);
        return "redirect:/printers";
    }

}
