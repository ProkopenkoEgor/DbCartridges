package com.example.springbootsbyt.controller;

import com.example.springbootsbyt.model.*;
import com.example.springbootsbyt.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    private final PartyLotsServiceImpl partyLotsServiceImpl;


    @Autowired
    public SearchController(CartridgeServiceImpl cartridgeServiceImpl,
                            CartrsServiceImpl cartrsServiceImpl,
                            HistoryServiceImpl historyServiceImpl,
                            PrintersServiceImpl printersServiceImpl, ManufacturerServiceImpl manufacturerServiceImpl, PartyLotsServiceImpl partyLotsServiceImpl){
        this.cartridgeServiceImpl = cartridgeServiceImpl;
        this.cartrsServiceImpl = cartrsServiceImpl;
        this.historyServiceImpl = historyServiceImpl;
        this.printersServiceImpl = printersServiceImpl;
        this.manufacturerServiceImpl = manufacturerServiceImpl;
        this.partyLotsServiceImpl = partyLotsServiceImpl;
    }
    @GetMapping("/search")
    public String findAllSearch() {
        return "search";
    }

    @PostMapping("/Data-Search-Dispose")
    public String DataSearchDispose(Date dt1, Date dt2, Model model, @Param("firstSelect") String firstSelect,
                                    @Param("partylotsSelect") String partylotsSelect){
        if(partylotsSelect != null){
            if(firstSelect.equals("1")){
                List<Partylots> partylots1 = partyLotsServiceImpl.findDsLotNumberByPartyStatus0Or1();
                List<History> histories = historyServiceImpl.findByDateOfStatusBetweenDate1AndDate2(dt1, dt2);
                model.addAttribute("history", histories);
                model.addAttribute("partylots1", partylots1);
                return "result-DataSearch";
            }
            if(firstSelect.equals("2")){
                List<Partylots> partylots1 = partyLotsServiceImpl.findDsLotNumberByPartyStatus2();
                List<History> histories = historyServiceImpl.findByDateOfStatusBetweenDate1AndDate2(dt1, dt2);
                model.addAttribute("history", histories);
                model.addAttribute("partylots1", partylots1);
                return "result-DataSearch";
            }
            if(firstSelect.equals("3")){
                List<Partylots> partylots1 = partyLotsServiceImpl.findDsLotNumberByPartyStatus3Or4();
                List<History> histories = historyServiceImpl.findByDateOfStatusBetweenDate1AndDate2(dt1, dt2);
                model.addAttribute("history", histories);
                model.addAttribute("partylots1", partylots1);
                return "result-DataSearch";
            }
            if(firstSelect.equals("4")){
                List<Partylots> partylots1 = partyLotsServiceImpl.findDsLotNumberByPartyStatus5();
                List<History> histories = historyServiceImpl.findByDateOfStatusBetweenDate1AndDate2(dt1, dt2);
                model.addAttribute("history", histories);
                model.addAttribute("partylots1", partylots1);
                return "result-DataSearch";
            }
            if(firstSelect.equals("5")){
                List<Partylots> partylots1 = partyLotsServiceImpl.findDsLotNumber();
                List<History> histories = historyServiceImpl.findByDateOfStatusBetweenDate1AndDate2(dt1, dt2);
                model.addAttribute("history", histories);
                model.addAttribute("partylots1", partylots1);
                return "result-DataSearch";
            }
        }
        if (firstSelect.equals("1")){
            List<Cartridges> cartridges = cartridgeServiceImpl.findAllByPartyStatus0(dt1, dt2);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            List<Partylots> partylots = partyLotsServiceImpl.findDsCartridgesId();
            model.addAttribute("cartridges", cartridges);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("partylots", partylots);
            return "searchForUsedCartridges";
        }
        if (firstSelect.equals("2")){
            List<Cartridges> cartridges = cartridgeServiceImpl.findAllByPartyStatus1And2(dt1, dt2);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            List<Partylots> partylots = partyLotsServiceImpl.findDsCartridgesId();
            model.addAttribute("cartridges", cartridges);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("partylots", partylots);
            return "searchForUsedCartridges";
        }
        if (firstSelect.equals("3")){
            List<Cartridges> cartridges = cartridgeServiceImpl.findAllByPartyStatus3(dt1, dt2);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            List<Partylots> partylots = partyLotsServiceImpl.findDsCartridgesId();
            model.addAttribute("cartridges", cartridges);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("partylots", partylots);
            return "searchForUsedCartridges";
        }
        if (firstSelect.equals("4")){
            List<Cartridges> cartridges = cartridgeServiceImpl.findAllByPartyStatus4And5(dt1, dt2);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            List<Partylots> partylots = partyLotsServiceImpl.findDsCartridgesId();
            model.addAttribute("cartridges", cartridges);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("partylots", partylots);
            return "searchForUsedCartridges";
        }
        if (firstSelect.equals("5")){
            List<Cartridges> cartridges1 = cartridgeServiceImpl.findAllByPartyStatus(dt1,dt2);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            List<Partylots> partylots = partyLotsServiceImpl.findDsCartridgesId();
            model.addAttribute("cartridges", cartridges1);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("partylots", partylots);
            return "searchForUsedCartridges";
        }
        return "searchForUsedCartridges";
    }
}
