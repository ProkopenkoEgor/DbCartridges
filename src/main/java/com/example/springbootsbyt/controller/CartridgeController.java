package com.example.springbootsbyt.controller;

import com.example.springbootsbyt.model.Cartridges;
import com.example.springbootsbyt.model.Cartrs;
//import com.example.springbootsbyt.entity.Cartridges;
//import com.example.springbootsbyt.entity.Cartrs;
import com.example.springbootsbyt.model.History;
import com.example.springbootsbyt.service.impl.CartridgeServiceImpl;
import com.example.springbootsbyt.service.impl.CartrsServiceImpl;
//import com.example.springbootsbyt.service.impl.HistoryServiceImpl;
import com.example.springbootsbyt.service.impl.HistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CartridgeController {

    private final CartridgeServiceImpl cartridgeServiceImpl;
    private final CartrsServiceImpl cartrsServiceImpl;
    private final HistoryServiceImpl historyServiceImpl;

    @Autowired
    public CartridgeController(CartridgeServiceImpl cartridgeServiceImpl,
                               CartrsServiceImpl cartrsServiceImpl,
                               HistoryServiceImpl historyServiceImpl) {
        this.cartridgeServiceImpl = cartridgeServiceImpl;
        this.cartrsServiceImpl = cartrsServiceImpl;
        this.historyServiceImpl = historyServiceImpl;
    }

    @GetMapping("/cartridges")
    public String findAll(Model model) {
        List<Cartridges> cartridges = cartridgeServiceImpl.findAll();
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        List<History> history = historyServiceImpl.findAll();
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("history", history);
        return "cartridge-list";
    }

    @GetMapping("/cartridge-create")
    public String createCartridgeForm(Model model, Cartridges cartridge) {
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        model.addAttribute("cartrs", cartrs);
        return "cartridge-create";
    }

    @PostMapping("/cartridge-create")
    public String createCartridge(Cartridges cartridge) {
        cartridgeServiceImpl.saveCartridge(cartridge);
        return "redirect:/cartridges";
    }

    @GetMapping("/cartridge-update/{id}")
    public String updateCartridgeForm(@PathVariable("id") int id, Model model) {
        Cartridges cartridge = cartridgeServiceImpl.findById(id);
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        model.addAttribute("cartridge", cartridge);
        model.addAttribute("cartrs", cartrs);
        return "cartridge-update";
    }

    @PostMapping("/cartridge-update")
    public String updateUser(Cartridges cartridge) {
        cartridgeServiceImpl.saveCartridge(cartridge);
        return "redirect:/cartridges";
    }
    @GetMapping("/cartridge-moreInfo/{id}")
    public String moreInfoForm(@PathVariable("id") int id, Model model) {
        Cartridges cartridges = cartridgeServiceImpl.findById(id);
        List<History> history = historyServiceImpl.findAll();
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("history", history);
        return "cartridge-moreinfo";
    }

    @GetMapping("/cartridge-delete/{id}")
    public String deleteCartridge(@PathVariable("id") int id) {
        cartridgeServiceImpl.deleteById(id);
        return "redirect:/cartridges";
    }
}
