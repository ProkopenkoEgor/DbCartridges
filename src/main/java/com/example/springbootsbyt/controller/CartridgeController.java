package com.example.springbootsbyt.controller;

import com.example.springbootsbyt.model.Cartridges;
import com.example.springbootsbyt.model.Cartrs;
import com.example.springbootsbyt.model.History;
import com.example.springbootsbyt.model.Printers;
import com.example.springbootsbyt.service.impl.CartridgeServiceImpl;
import com.example.springbootsbyt.service.impl.CartrsServiceImpl;
import com.example.springbootsbyt.service.impl.HistoryServiceImpl;
import com.example.springbootsbyt.service.impl.PrintersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class CartridgeController {

    private final CartridgeServiceImpl cartridgeServiceImpl;
    private final CartrsServiceImpl cartrsServiceImpl;
    private final HistoryServiceImpl historyServiceImpl;
    private final PrintersServiceImpl printersServiceImpl;


    @Autowired
    public CartridgeController(CartridgeServiceImpl cartridgeServiceImpl,
                               CartrsServiceImpl cartrsServiceImpl,
                               HistoryServiceImpl historyServiceImpl,
                               PrintersServiceImpl printersServiceImpl){
        this.cartridgeServiceImpl = cartridgeServiceImpl;
        this.cartrsServiceImpl = cartrsServiceImpl;
        this.historyServiceImpl = historyServiceImpl;
        this.printersServiceImpl = printersServiceImpl;
    }

    @GetMapping("/cartridges")
    public String findAllCartridges(Model model) {
        List<Cartridges> cartridges = cartridgeServiceImpl.findAll();
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        List<History> history = historyServiceImpl.findAll();
        List<Printers> printers = printersServiceImpl.findAll();
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("history", history);
        model.addAttribute("printers", printers);
        return "cartridge-list";

    }

    @GetMapping("/cartridge-create")
    public String createCartridgeForm(Model model, Cartridges cartridge) {
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        List<Printers> printers = printersServiceImpl.findAll();
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("printers", printers);
        return "cartridge-create";
    }

    @PostMapping("/cartridge-create")
    public String createCartridge(@Valid Cartridges cartridge, BindingResult bindingResult, Model model){
        try {if (bindingResult.hasErrors()){
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            List<Printers> printers = printersServiceImpl.findAll();
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("printers", printers);
            model.addAttribute("cartridges", cartridge);
            return "cartridge-create";
        }
        }catch(NumberFormatException e){
            throw new ValidationException("lox");
        }
        cartridgeServiceImpl.saveCartridge(cartridge);
        return "redirect:/cartridges";
    }


    @GetMapping("/cartridge-update/{id}")
    public String updateCartridgeForm(@PathVariable("id") int id, Model model) {
        Cartridges cartridge = cartridgeServiceImpl.findById(id);
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        List<Printers> printers = printersServiceImpl.findAll();
        model.addAttribute("cartridge", cartridge);
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("printers", printers);
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
