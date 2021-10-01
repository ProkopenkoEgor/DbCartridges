package com.example.springbootsbyt.controller;

import com.example.springbootsbyt.model.Cartridges;
import com.example.springbootsbyt.model.Cartrs;
import com.example.springbootsbyt.model.History;
import com.example.springbootsbyt.model.Printers;
import com.example.springbootsbyt.service.impl.CartrsServiceImpl;
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

    @Autowired
    public CartrsController(CartrsServiceImpl cartrsServiceImpl) {
        this.cartrsServiceImpl = cartrsServiceImpl;
    }

    @GetMapping("/cartrs")
    public String findAllCartrs(Model model) {
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        model.addAttribute("cartrs", cartrs);
        return "cartrs-list";

    }

    @GetMapping("/cartrs-create")
    public String createCartrsForm(Cartrs cartrs) {
        return "cartrs-create";
    }

    @PostMapping("/cartrs-create")
    public String createCartrs(Cartrs cartrs) {
        cartrsServiceImpl.saveCartrs(cartrs);
        return "redirect:/cartridges";
    }

    @GetMapping("cartrs-update/{idCartrs}")
    public String updateCartrsForm(@PathVariable("idCartrs") int id, Model model) {
        Cartrs cartrs = cartrsServiceImpl.findById(id);
        model.addAttribute("cartrs", cartrs);
        return "/cartrs-update";
    }

    @PostMapping("/cartrs-update")
    public String updateUser(Cartrs cartrs) {
        cartrsServiceImpl.saveCartrs(cartrs);
        return "redirect:/cartridges";
    }

    @GetMapping("cartrs-delete/{idCartrs}")
    public String deleteCartrs(@PathVariable("idCartrs") int id) {
        cartrsServiceImpl.deleteById(id);
        return "redirect:/cartridges";
    }
}
