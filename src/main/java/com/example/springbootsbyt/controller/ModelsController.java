package com.example.springbootsbyt.controller;

import com.example.springbootsbyt.model.Cartrs;
import com.example.springbootsbyt.model.Printers;
import com.example.springbootsbyt.service.impl.CartrsServiceImpl;
import com.example.springbootsbyt.service.impl.PrintersServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ModelsController {
    private final CartrsServiceImpl cartrsServiceImpl;
    private final PrintersServiceImpl printersServiceImpl;

    public ModelsController(CartrsServiceImpl cartrsServiceImpl, PrintersServiceImpl printersServiceImpl) {
        this.cartrsServiceImpl = cartrsServiceImpl;
        this.printersServiceImpl = printersServiceImpl;
    }
    @GetMapping("/models")
    public String findAllCartridges(Model model) {
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        List<Printers> printers = printersServiceImpl.findAll();
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("printers", printers);
        return "models";
    }
}
