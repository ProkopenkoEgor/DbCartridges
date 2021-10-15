package com.example.springbootsbyt.controller;

import com.example.springbootsbyt.model.Cartrs;
import com.example.springbootsbyt.model.Printers;
import com.example.springbootsbyt.service.impl.CartrsServiceImpl;
import com.example.springbootsbyt.service.impl.PrintersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CartrsController {
    private final CartrsServiceImpl cartrsServiceImpl;
    private final PrintersServiceImpl printersServiceImpl;

    @Autowired
    public CartrsController(CartrsServiceImpl cartrsServiceImpl, PrintersServiceImpl printersServiceImpl) {
        this.cartrsServiceImpl = cartrsServiceImpl;
        this.printersServiceImpl = printersServiceImpl;
    }

    @GetMapping("/cartrs")
    public String findAllCartrs(Model model) {
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        model.addAttribute("cartrs", cartrs);
        return "cartrs-list";

    }

    @GetMapping("/cartrs-create")
    public String createCartrsForm(Cartrs cartrs, Model model) {
        List<Printers> printers = printersServiceImpl.findAll();
        model.addAttribute("printers", printers);
        return "cartrs-create";
    }

    @PostMapping("/cartrs-create")
    public String createCartrs(Cartrs cartrs) {
        cartrsServiceImpl.saveCartrs(cartrs);
        return "redirect:/cartrs";
    }

    @GetMapping("cartrs-update/{idCartrs}")
    public String updateCartrsForm(@PathVariable("idCartrs") int id, Model model) {
        Cartrs cartrs = cartrsServiceImpl.findById(id);
        List<Printers> printers = printersServiceImpl.findAll();
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("printers", printers);
        return "cartrs-update";
    }

    @PostMapping("/cartrs-update")
    public String updateUser(Cartrs cartrs) {
        cartrsServiceImpl.saveCartrs(cartrs);
        return "redirect:/cartrs";
    }

    @GetMapping("cartrs-delete/{idCartrs}")
    public String deleteCartrs(@PathVariable("idCartrs") int id) {
        cartrsServiceImpl.deleteById(id);
        return "redirect:/cartrs";
    }
}
