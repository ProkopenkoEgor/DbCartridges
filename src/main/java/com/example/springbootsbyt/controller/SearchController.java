package com.example.springbootsbyt.controller;

import com.example.springbootsbyt.model.*;
import com.example.springbootsbyt.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.util.List;

@Controller
public class SearchController {
    private final CartridgeServiceImpl cartridgeServiceImpl;
    private final CartrsServiceImpl cartrsServiceImpl;
    private final HistoryServiceImpl historyServiceImpl;
    private final PrintersServiceImpl printersServiceImpl;
    private final ManufacturerServiceImpl manufacturerServiceImpl;


    @Autowired
    public SearchController(CartridgeServiceImpl cartridgeServiceImpl,
                            CartrsServiceImpl cartrsServiceImpl,
                            HistoryServiceImpl historyServiceImpl,
                            PrintersServiceImpl printersServiceImpl, ManufacturerServiceImpl manufacturerServiceImpl){
        this.cartridgeServiceImpl = cartridgeServiceImpl;
        this.cartrsServiceImpl = cartrsServiceImpl;
        this.historyServiceImpl = historyServiceImpl;
        this.printersServiceImpl = printersServiceImpl;
        this.manufacturerServiceImpl = manufacturerServiceImpl;
    }
    @GetMapping("/search")
    public String findAllSearch() {
        return "search";
    }
    @PostMapping("/search")
    public String Search(Model model, String keyword){
        List<Cartridges> cartridges = cartridgeServiceImpl.findAll(keyword);
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        List<Printers> printers = printersServiceImpl.findAll();
        List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("keyword", keyword);
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("printers", printers);
        model.addAttribute("manufacturers",manufacturers);
        return "cartridge-list";
    }

    @PostMapping("/Data-Search")
    public String DataSearch(Date Date, Model model){
        List<History> histories = historyServiceImpl.findByDateOfStatus(Date);
        List<Cartridges> cartridges = cartridgeServiceImpl.findAll();
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        List<Printers> printers = printersServiceImpl.findAll();
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("history", histories);
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("printers", printers);
        return "result-DataSearch";
    }
}
