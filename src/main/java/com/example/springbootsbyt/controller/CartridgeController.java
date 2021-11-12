package com.example.springbootsbyt.controller;

import com.example.springbootsbyt.model.*;
import com.example.springbootsbyt.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CartridgeController {

    private final CartridgeServiceImpl cartridgeServiceImpl;
    private final CartrsServiceImpl cartrsServiceImpl;
    private final HistoryServiceImpl historyServiceImpl;
    private final PrintersServiceImpl printersServiceImpl;
    private final ManufacturerServiceImpl manufacturerServiceImpl;


    @Autowired
    public CartridgeController(CartridgeServiceImpl cartridgeServiceImpl,
                               CartrsServiceImpl cartrsServiceImpl,
                               HistoryServiceImpl historyServiceImpl,
                               PrintersServiceImpl printersServiceImpl, ManufacturerServiceImpl manufacturerServiceImpl){
        this.cartridgeServiceImpl = cartridgeServiceImpl;
        this.cartrsServiceImpl = cartrsServiceImpl;
        this.historyServiceImpl = historyServiceImpl;
        this.printersServiceImpl = printersServiceImpl;
        this.manufacturerServiceImpl = manufacturerServiceImpl;
    }

    @GetMapping("/cartridges")
    public String findAllCartridges(Model model) {
        List<Cartridges> cartridges = cartridgeServiceImpl.findAll();
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        List<History> history = historyServiceImpl.findAll();
        List<Printers> printers = printersServiceImpl.findAll();
        List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("history", history);
        model.addAttribute("printers", printers);
        model.addAttribute("manufacturers",manufacturers);
        return "cartridge-list";
    }

    @GetMapping("/cartridge-create")
    public String createCartridgeForm(Model model, Cartridges cartridge) {
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        List<Printers> printers = printersServiceImpl.findAll();
        List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("printers", printers);
        model.addAttribute("manufacturers",manufacturers);
        return "cartridge-create";
    }

    @PostMapping("/cartridge-create")
    public String createCartridge(@Valid Cartridges cartridge, BindingResult bindingResult, Model model){
       if (bindingResult.hasErrors()) {
           List<Cartrs> cartrs = cartrsServiceImpl.findAll();
           List<Printers> printers = printersServiceImpl.findAll();
           List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
           model.addAttribute("cartrs", cartrs);
           model.addAttribute("printers", printers);
           model.addAttribute("manufacturers",manufacturers);
           return "cartridge-create";
           }
            Cartridges cartridge1 = null;
            List<Cartridges> cartridges1 = cartridgeServiceImpl.findAll();
            model.addAttribute("cartridges", cartridge);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            model.addAttribute("cartrs", cartrs);
            String str = cartridge.getInventoryNumber();
            for (int i = 0; i < cartridges1.size(); i++) {
                cartridge1 = cartridges1.get(i);
                if (str.equalsIgnoreCase(cartridge1.getInventoryNumber()) == true) {
                    bindingResult.rejectValue("inventoryNumber", "error.inventoryNumber", "Такой инвентарный номер уже есть");
                    return "cartridge-create";
                }
            }
        cartridgeServiceImpl.saveCartridge(cartridge);
        return "redirect:/cartridges";
    }



    @GetMapping("/cartridge-update/{id}")
    public String updateCartridgeForm(@PathVariable("id") int id, Model model) {
        Cartridges cartridge = cartridgeServiceImpl.findById(id);
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        model.addAttribute("cartridges", cartridge);
        model.addAttribute("cartrs", cartrs);
        return "cartridge-update";
    }

    @PostMapping("/cartridge-update/{id}")
    public String updateCartridgeForm1(@PathVariable("id") int id,@Valid  Cartridges cartridge,BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            model.addAttribute("cartrs", cartrs);
            return "cartridge-update";
        }
        String str = cartridge.getInventoryNumber();
        Cartridges cartridges2 = cartridgeServiceImpl.findById(id);
        String str2 = cartridges2.getInventoryNumber();
        if (str.equalsIgnoreCase(str2)) {
            cartridgeServiceImpl.saveCartridge(cartridge);
            return "redirect:/cartridges";
        }else {
            Cartridges cartridge1 = null;
            List<Cartridges> cartridges1 = cartridgeServiceImpl.findAll();
            model.addAttribute("cartridges", cartridge);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            model.addAttribute("cartrs", cartrs);
            for (int i = 0; i < cartridges1.size(); i++) {
                cartridge1 = cartridges1.get(i);
                if (str.equalsIgnoreCase(cartridge1.getInventoryNumber())) {
                    bindingResult.rejectValue("inventoryNumber", "error.inventoryNumber", "Такой инвентарный номер уже есть");
                    return "cartridge-update";
                }
            }
        }
        cartridgeServiceImpl.saveCartridge(cartridge);
        return "redirect:/cartridges";
    }

    @GetMapping("/cartridge-moreInfo/{id}")
    public String moreInfoForm(@PathVariable("id") int id, Model model) {
        Cartridges cartridges = cartridgeServiceImpl.findById(id);
        List<History> history = historyServiceImpl.findAll();
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("history", history);
        return "cartridge-moreInfo";
    }

    @GetMapping("/cartridge-delete/{id}")
    public String deleteCartridge(@PathVariable("id") int id) {
        cartridgeServiceImpl.deleteById(id);
        return "redirect:/cartridges";
    }
}
