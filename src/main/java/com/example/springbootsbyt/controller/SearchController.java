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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class SearchController {
    private final CartridgeServiceImpl cartridgeServiceImpl;
    private final CartrsServiceImpl cartrsServiceImpl;
    private final HistoryServiceImpl historyServiceImpl;
    private final PrintersServiceImpl printersServiceImpl;


    @Autowired
    public SearchController(CartridgeServiceImpl cartridgeServiceImpl,
                               CartrsServiceImpl cartrsServiceImpl,
                               HistoryServiceImpl historyServiceImpl,
                               PrintersServiceImpl printersServiceImpl){
        this.cartridgeServiceImpl = cartridgeServiceImpl;
        this.cartrsServiceImpl = cartrsServiceImpl;
        this.historyServiceImpl = historyServiceImpl;
        this.printersServiceImpl = printersServiceImpl;
    }
    @GetMapping("/search")
    public String findAll(Model model) {
        List<Cartridges> cartridges = cartridgeServiceImpl.findAll();
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        List<History> history = historyServiceImpl.findAll();
        List<Printers> printers = printersServiceImpl.findAll();
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("history", history);
        model.addAttribute("printers", printers);
        return "search";
    }
    @PostMapping("/search")
    public String Search(Model model, String keyword){
        List<Cartridges> cartridges = cartridgeServiceImpl.findAll(keyword);
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        List<Printers> printers = printersServiceImpl.findAll();
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("keyword", keyword);
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("printers", printers);
        return "result-search";
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
