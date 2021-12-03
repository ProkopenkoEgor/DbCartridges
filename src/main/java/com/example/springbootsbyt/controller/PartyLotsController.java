package com.example.springbootsbyt.controller;

import com.example.springbootsbyt.model.*;
import com.example.springbootsbyt.service.impl.*;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Controller
public class PartyLotsController {
    private final CartridgeServiceImpl cartridgeServiceImpl;
    private final CartrsServiceImpl cartrsServiceImpl;
    private final HistoryServiceImpl historyServiceImpl;
    private final PrintersServiceImpl printersServiceImpl;
    private final ManufacturerServiceImpl manufacturerServiceImpl;
    private final PartyLotsServiceImpl partyLotsServiceImpl;


    public PartyLotsController(CartridgeServiceImpl cartridgeServiceImpl, CartrsServiceImpl cartrsServiceImpl, HistoryServiceImpl historyServiceImpl, PrintersServiceImpl printersServiceImpl, ManufacturerServiceImpl manufacturerServiceImpl, PartyLotsServiceImpl partyLotsServiceImpl) {
        this.cartridgeServiceImpl = cartridgeServiceImpl;
        this.cartrsServiceImpl = cartrsServiceImpl;
        this.historyServiceImpl = historyServiceImpl;
        this.printersServiceImpl = printersServiceImpl;
        this.manufacturerServiceImpl = manufacturerServiceImpl;
        this.partyLotsServiceImpl = partyLotsServiceImpl;
    }
    @GetMapping("/lots")
    public String findAllLots(Model model,History history){
        model.addAttribute("history", history);
        return "partylots";
    }

    @GetMapping("/lotsHistory/{idHistory}")
    public String findAllLots1(@PathVariable ("idHistory") long idHistory,Model model){
        History history = historyServiceImpl.findById(idHistory);
        model.addAttribute("history",history);
        model.addAttribute("partylots",new Partylots());
        return "partylotshistory";
    }
    @PostMapping("/lotsNumber/{idHistory}")
    public String CreateLotsNumber(@PathVariable ("idHistory") long idHistory,@Valid Partylots NewPartylots,BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            History history = historyServiceImpl.findById(idHistory);
            model.addAttribute("history",history);
            return "partylotshistory";
        }
        String str = NewPartylots.getLotNumber();
        List<Partylots> partylots = partyLotsServiceImpl.findAllByLotNumber(NewPartylots.getLotNumber());
        Partylots partylots1 = null;
        for (int i = 0; i < partylots.size(); i++) {
            partylots1 = partylots.get(i);
            if (partylots1.getLotNumber().equals(NewPartylots.getLotNumber())) {
                History history = historyServiceImpl.findById(idHistory);
                model.addAttribute("history",history);
                bindingResult.rejectValue("lotNumber", "error.lotNumber", "Такой номер партии уже есть");
                return "partylotshistory";
            }
        }
        return "redirect:/CreateLots/{idHistory}/" + str;
    }

    @GetMapping("/CreateLots/{idHistory}/{lotNumber}")
    public String findAllLotsNumber(@PathVariable ("idHistory") long idHistory,@PathVariable ("lotNumber") String lotNumber,Model model){
        List<Cartridges> cartridges = cartridgeServiceImpl.findAll();
        History history = historyServiceImpl.findById(idHistory);
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("history", history);
        model.addAttribute("partylots",new Partylots());
        return "createpartylots";
    }

    @PostMapping("/CreateLots/{idHistory}/{lotNumber}")
    public String CreateLots(@PathVariable ("idHistory") long idHistory, @PathVariable ("lotNumber") String lotNumber,Partylots partylots,BindingResult bindingResult,Model model){
        String barcode = Long.toString(partylots.getCartridgesId());
        List<Cartridges> cartridges = cartridgeServiceImpl.findAllByInventoryNumber(barcode);
        if (cartridges.isEmpty()){
            History history = historyServiceImpl.findById(idHistory);
            model.addAttribute("history", history);
            bindingResult.rejectValue("cartridgesId", "error.cartridgesId", "Такой картридж не существует");
            return "createpartylots";
        }
        Cartridges cartridge = null;
        for (int i = 0; i < cartridges.size(); i++) {
            cartridge = cartridges.get(i);
            if (cartridge.getInventoryNumber().equals(barcode)) {
                Long beryID = cartridge.getId();
                partylots.setCartridgesId(beryID);
            }
        }
        partyLotsServiceImpl.savePartylots(partylots);
        return "redirect:/CreatePartyLots/{idHistory}/{lotNumber}";
    }

    @GetMapping("/CreatePartyLots/{idHistory}/{lotNumber}")
    public String CreatePartyLots(@PathVariable ("idHistory") long idHistory,@PathVariable ("lotNumber") String lotNumber,Model model){
        List<Cartridges> cartridges = cartridgeServiceImpl.findAll();
        List<Partylots> partylots1 = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        History history = historyServiceImpl.findById(idHistory);
        List<Printers> printers = printersServiceImpl.findAll();
        List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("history", history);
        model.addAttribute("printers", printers);
        model.addAttribute("manufacturers",manufacturers);
        model.addAttribute("partylots1",partylots1);
        model.addAttribute("partylots",new Partylots());
        return "partylotsfinal";
    }

    @PostMapping("/CreatePartyLots/{idHistory}/{lotNumber}")
    public String CreatePartyLots(@PathVariable ("idHistory") long idHistory, @PathVariable ("lotNumber") String lotNumber,Partylots partylots,BindingResult bindingResult,Model model){
        String barcode = Long.toString(partylots.getCartridgesId());
        List<Cartridges> cartridges = cartridgeServiceImpl.findAllByInventoryNumber(barcode);
        if (cartridges.isEmpty()){
            List<Cartridges> cartridges1 = cartridgeServiceImpl.findAll();
            List<Partylots> partylots1 = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            History history = historyServiceImpl.findById(idHistory);
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            model.addAttribute("cartridges", cartridges1);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("history", history);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("partylots1",partylots1);
            bindingResult.rejectValue("cartridgesId", "error.cartridgesId", "Такой картридж не существует");
            return "partylotsfinal";
        }
        Cartridges cartridge = null;
        for (int i = 0; i < cartridges.size(); i++) {
            cartridge = cartridges.get(i);
            if (cartridge.getInventoryNumber().equals(barcode)) {
                Long beryID = cartridge.getId();
                partylots.setCartridgesId(beryID);
            }
        }
        partyLotsServiceImpl.savePartylots(partylots);
        return "redirect:/CreatePartyLots/{idHistory}/{lotNumber}";
    }


    @GetMapping("/lots-delete/{idPartylots}/{idHistory}/{lotNumber}")
    public String deletePartyLots(@PathVariable("idPartylots") long idPartylots,@PathVariable ("idHistory") int idHistory,@PathVariable ("lotNumber") String lotNumber) {
        partyLotsServiceImpl.deleteById(idPartylots);
        return "redirect:/CreatePartyLots/{idHistory}/{lotNumber}";
    }

    @GetMapping("/lots1-delete/{idPartylots}/{idHistory}/{lotNumber}")
    public String deletePartyLotsView(@PathVariable("idPartylots") long idPartylots,@PathVariable ("idHistory") int idHistory,@PathVariable ("lotNumber") String lotNumber) {
        partyLotsServiceImpl.deleteById(idPartylots);
        return "redirect:/view-lots/{lotNumber}/{idHistory}";
    }

    @GetMapping("/lots-look")
    public String findAllLots(Model model){
        List<Partylots> partylots1 = partyLotsServiceImpl.findDsLotNumber();
        List<History> histories = historyServiceImpl.findAll();
        model.addAttribute("partylots1",partylots1);
        model.addAttribute("history",histories);
        return "partylots-selector";
    }

    @GetMapping("/view-lots/{lotNumber}/{idHistory}")
    public String viewLots(@PathVariable ("lotNumber") String lotNumber,@PathVariable ("idHistory") long idHistory,Model model){
        List<Cartridges> cartridges = cartridgeServiceImpl.findAll();
        List<Partylots> partylots1 = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        List<History> history = historyServiceImpl.findAll();
        List<Printers> printers = printersServiceImpl.findAll();
        List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("history", history);
        model.addAttribute("printers", printers);
        model.addAttribute("manufacturers",manufacturers);
        model.addAttribute("partylots1",partylots1);
        model.addAttribute("partylots",new Partylots());
        return "partylots-list";
    }

    @PostMapping("/view-lots/{lotNumber}/{idHistory}")
    public String CreatePartyLotsView(@PathVariable ("idHistory") long idHistory, @PathVariable ("lotNumber") String lotNumber,Partylots partylots,BindingResult bindingResult,Model model){
        String barcode = Long.toString(partylots.getCartridgesId());
        List<Cartridges> cartridges = cartridgeServiceImpl.findAllByInventoryNumber(barcode);
        if (cartridges.isEmpty()){
            List<Cartridges> cartridges1 = cartridgeServiceImpl.findAll();
            List<Partylots> partylots1 = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            History history = historyServiceImpl.findById(idHistory);
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            model.addAttribute("cartridges", cartridges1);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("history", history);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("partylots1",partylots1);
            bindingResult.rejectValue("cartridgesId", "error.cartridgesId", "Такой картридж не существует");
            return "partylots-list";
        }
        Cartridges cartridge = null;
        for (int i = 0; i < cartridges.size(); i++) {
            cartridge = cartridges.get(i);
            if (cartridge.getInventoryNumber().equals(barcode)) {
                Long beryID = cartridge.getId();
                partylots.setCartridgesId(beryID);
            }
        }
        partyLotsServiceImpl.savePartylots(partylots);
        return "redirect:/view-lots/{lotNumber}/{idHistory}";
    }

    @GetMapping("/lots-look-history")
    public String findAllLotsHistory(Model model){
        List<Partylots> partylots = partyLotsServiceImpl.findDsHistory();
        List<History> histories = historyServiceImpl.findAll();
        model.addAttribute("partylots",partylots);
        model.addAttribute("history",histories);
        return "partylots-history-selector";
    }

    @GetMapping("/view-lots-history/{idHistory}")
    public String viewLotsHistory(@PathVariable ("idHistory") long idHistory,Model model){
        List<History> history = historyServiceImpl.findAll();
        List<Partylots> partylots1 = partyLotsServiceImpl.findDsHistoryKeyword(idHistory);
        model.addAttribute("history",history);
        model.addAttribute("partylots1",partylots1);
        return "partylots-list-history";
    }

    @GetMapping("/ComparePartyLots/{idHistory}/{lotNumber}")
    public String ComparePartyLots(@PathVariable ("idHistory") long idHistory,@PathVariable ("lotNumber") String lotNumber,Model model){
        List<Partylots> partylots1 = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
        Partylots partylots12 = null;
        for (int i=0; i<partylots1.size();i++){
            partylots12 = partylots1.get(i);
            if (partylots12.getHistoryIdHistoryReturn() != null){
                return "redirect:/ComparisonPartyLots/{idHistory}/" + partylots12.getHistoryIdHistoryReturn() +"/{lotNumber}";
            }
        }
        List<Cartridges> cartridges = cartridgeServiceImpl.findAll();
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        History history = historyServiceImpl.findById(idHistory);
        List<Printers> printers = printersServiceImpl.findAll();
        List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("history", history);
        model.addAttribute("history1", new History());
        model.addAttribute("printers", printers);
        model.addAttribute("manufacturers",manufacturers);
        model.addAttribute("partylots1",partylots1);
        model.addAttribute("partylots",new Partylots());
        return "create-return-partylots";
    }

    @GetMapping("/ComparisonPartyLots/{idHistory}/{idHistoryReturn}/{lotNumber}")
    public String ComparisonPartyLots(@PathVariable ("idHistory") long idHistory, @PathVariable("idHistoryReturn") long idHistoryReturn, @PathVariable ("lotNumber") String lotNumber,Model model,
                                      @ModelAttribute("NewCartridge") Cartridges NewCartridge){
        List<Cartridges> cartridges = cartridgeServiceImpl.findAll();
        List<Partylots> partylots1 = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        History history = historyServiceImpl.findById(idHistory);
        History history1 = historyServiceImpl.findById(idHistoryReturn);
        List<Printers> printers = printersServiceImpl.findAll();
        List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("history", history);
        model.addAttribute("history1", history1);
        model.addAttribute("printers", printers);
        model.addAttribute("manufacturers",manufacturers);
        model.addAttribute("partylots1",partylots1);
        model.addAttribute("partylots",new Partylots());
        return "comparepartylots";
    }

    @GetMapping("/ComparisonPartyLotsLookForCheck/{idHistory}/{idHistoryReturn}/{lotNumber}/{idPartylots}")
    public String ComparisonPartyLotsLookForCheck(@PathVariable ("idHistory") long idHistory, @PathVariable("idHistoryReturn") long idHistoryReturn, @PathVariable ("lotNumber") String lotNumber,@PathVariable("idPartylots") long idPartylots, Model model,
                                      @ModelAttribute("NewCartridge") Cartridges NewCartridge){
        List<Cartridges> cartridges = cartridgeServiceImpl.findAll();
        List<Partylots> partylots1 = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        History history = historyServiceImpl.findById(idHistory);
        History history1 = historyServiceImpl.findById(idHistoryReturn);
        List<Printers> printers = printersServiceImpl.findAll();
        List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
        Partylots partylotsOne = partyLotsServiceImpl.findById(idPartylots);
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("history", history);
        model.addAttribute("history1", history1);
        model.addAttribute("printers", printers);
        model.addAttribute("manufacturers",manufacturers);
        model.addAttribute("partylots1",partylots1);
        model.addAttribute("partylotsOne",partylotsOne);
        model.addAttribute("partylots",new Partylots());
        return "comparepartylots";
    }

    @PostMapping("/Compare/{lotNumber}/{idHistory}/{idHistoryReturn}")
    public String ComparisonPartyLotsView(@PathVariable ("idHistory") long idHistory, @PathVariable("idHistoryReturn") long idHistoryReturn, @PathVariable ("lotNumber") String lotNumber,
                                          Partylots NewPartylots, @Valid @ModelAttribute("NewCartridge") Cartridges NewCartridge, BindingResult bindingResult, Model model){
        List<Partylots> partylotsEquals = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
        Cartridges cartridges11 = cartridgeServiceImpl.findByInventoryNumber(NewCartridge.getInventoryNumber());
        if (bindingResult.hasErrors()){
            List<Cartridges> cartridges1 = cartridgeServiceImpl.findAll();
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            History history = historyServiceImpl.findById(idHistory);
            History history1 = historyServiceImpl.findById(idHistoryReturn);
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            model.addAttribute("cartridges", cartridges1);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("history", history);
            model.addAttribute("history1", history1);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("partylots1", partylotsEquals);
            return "comparepartylots";
        }
        if(cartridges11 != null){
            NewPartylots.setCartridgesId(cartridges11.getId());
        }else {
            List<Cartridges> cartridges1 = cartridgeServiceImpl.findAll();
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            History history = historyServiceImpl.findById(idHistory);
            History history1 = historyServiceImpl.findById(idHistoryReturn);
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            model.addAttribute("cartridges", cartridges1);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("history", history);
            model.addAttribute("history1", history1);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("partylots1", partylotsEquals);
            bindingResult.rejectValue("inventoryNumber", "error.inventoryNumber", "Такой картридж не существует");
            return "comparepartylots";
        }
        Partylots partylots1 = null;
        for (int i=0; i<partylotsEquals.size();i++){
            partylots1 = partylotsEquals.get(i);
            if (partylots1.getCartridgesId().equals(NewPartylots.getCartridgesId())){
                if (partylots1.getPartyStatus() == 1){
                    List<Cartridges> cartridges1 = cartridgeServiceImpl.findAll();
                    List<Cartrs> cartrs = cartrsServiceImpl.findAll();
                    History history = historyServiceImpl.findById(idHistory);
                    History history1 = historyServiceImpl.findById(idHistoryReturn);
                    List<Printers> printers = printersServiceImpl.findAll();
                    List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
                    model.addAttribute("cartridges", cartridges1);
                    model.addAttribute("cartrs", cartrs);
                    model.addAttribute("history", history);
                    model.addAttribute("history1", history1);
                    model.addAttribute("printers", printers);
                    model.addAttribute("manufacturers", manufacturers);
                    model.addAttribute("partylots1", partylotsEquals);
                    bindingResult.rejectValue("inventoryNumber", "error.inventoryNumber", "Такой картридж уже принят");
                    return "comparepartylots";
                }
                partylots1.setPartyStatus((long) 1);
                partylots1.setHistoryIdHistoryReturn(NewPartylots.getHistoryIdHistoryReturn());
            }else {
                long longa = NewPartylots.getCartridgesId();
                List<Partylots> partylotsList = partyLotsServiceImpl.findAllByCartridgesId(longa);
                model.addAttribute("partylotsList",partylotsList);
                return "redirect:/SelectOneOrSecondPartyLots/" + longa + "/{idHistory}/{idHistoryReturn}/{lotNumber}";
            }
        }
        partyLotsServiceImpl.savePartylots(partylots1);
        return "redirect:/ComparisonPartyLotsLookForCheck/{idHistory}/{idHistoryReturn}/{lotNumber}/" + partylots1.getIdPartylots();
    }

    @GetMapping("/SelectOneOrSecondPartyLots/{cartridgesId}/{idHistory}/{idHistoryReturn}/{lotNumber}")
    public String SelectOneOrSecondPartyLots(@PathVariable("cartridgesId") long cartridgesId,@PathVariable ("idHistory") long idHistory,
                                             @PathVariable("idHistoryReturn") long idHistoryReturn, @PathVariable ("lotNumber") String lotNumber,Partylots SecondPartylots,
                                             Model model){
        List<Partylots> partylotsList = partyLotsServiceImpl.findAllByCartridgesId(cartridgesId);
        List<History> historiesList = historyServiceImpl.findAll();
        List<Cartridges> cartridges = cartridgeServiceImpl.findAll();
        List<Partylots> partylots1 = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        List<History> history = historyServiceImpl.findAll();
        List<Printers> printers = printersServiceImpl.findAll();
        List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("history", history);
        model.addAttribute("printers", printers);
        model.addAttribute("manufacturers",manufacturers);
        model.addAttribute("partylots1",partylots1);
        model.addAttribute("partylotsList",partylotsList);
        model.addAttribute("history",historiesList);
        return "SelectSecondPartylots";
    }

    @PostMapping("/main/{idHistory}/{idHistoryReturn}/{lotNumber}")
    public String finishComparePartylots(@PathVariable ("idHistory") long idHistory,@PathVariable("idHistoryReturn") long idHistoryReturn, @PathVariable ("lotNumber") String lotNumber
            ,Partylots NewPartylots,BindingResult bindingResult,Model model){
        List<Partylots> partylots = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
        Partylots partylots2 = null;
        for (int i=0;i<partylots.size();i++){
            partylots2 = partylots.get(i);
            if (partylots2.getPartyStatus()==1){
                partylots2.setPartyStatus((long) 2);
            }else {
                List<Cartridges> cartridges1 = cartridgeServiceImpl.findAll();
                List<Cartrs> cartrs = cartrsServiceImpl.findAll();
                History history = historyServiceImpl.findById(idHistory);
                History history1 = historyServiceImpl.findById(idHistoryReturn);
                List<Printers> printers = printersServiceImpl.findAll();
                List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
                model.addAttribute("cartridges", cartridges1);
                model.addAttribute("cartrs", cartrs);
                model.addAttribute("history", history);
                model.addAttribute("history1", history1);
                model.addAttribute("printers", printers);
                model.addAttribute("manufacturers",manufacturers);
                model.addAttribute("partylots1",partylots);
                bindingResult.rejectValue("cartridgesId", "error.cartridgesId", "Партия не может быть закрыта, так как, не все картриджи пришли");
                return "comparepartylots";
            }
        }
        partyLotsServiceImpl.savePartylots(partylots2);
        return "redirect:/main";
    }

    @GetMapping("/lots-check/{idPartylots}/{idHistory}/{idHistoryReturn}/{lotNumber}")
    public String checkOnePartyLots(@PathVariable("idPartylots") long idPartylots,@PathVariable ("idHistory") long idHistory,@PathVariable("idHistoryReturn") long idHistoryReturn, @PathVariable ("lotNumber") String lotNumber){
        Partylots partylots = partyLotsServiceImpl.findById(idPartylots);
        partylots.setPartyStatus((long) 1);
        partylots.setHistoryIdHistoryReturn(idHistoryReturn);
        partyLotsServiceImpl.savePartylots(partylots);
        return "redirect:/ComparisonPartyLots/{idHistory}/{idHistoryReturn}/{lotNumber}";
    }

    @GetMapping("/lots-check-return/{idPartylots}/{idHistory}/{idHistoryReturn}/{lotNumber}")
    public String checkOnePartyLotsReturn(@PathVariable("idPartylots") long idPartylots,@PathVariable ("idHistory") long idHistory,@PathVariable("idHistoryReturn") long idHistoryReturn, @PathVariable ("lotNumber") String lotNumber){
        Partylots partylots = partyLotsServiceImpl.findById(idPartylots);
        partylots.setPartyStatus((long) 0);
        partylots.setHistoryIdHistoryReturn(null);
        partyLotsServiceImpl.savePartylots(partylots);
        return "redirect:/ComparisonPartyLots/{idHistory}/{idHistoryReturn}/{lotNumber}";
    }

    @GetMapping("/addComments/{idHistory}/{lotNumber}/{idPartylots}")
    public String addCommentsForPartylots(@PathVariable ("idHistory") long idHistory, @PathVariable ("lotNumber") String lotNumber,@PathVariable("idPartylots") long idPartylots, Model model) {
        Partylots partylots = partyLotsServiceImpl.findById(idPartylots);
        List<Cartridges> cartridges = cartridgeServiceImpl.findAll();
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        List<Printers> printers = printersServiceImpl.findAll();
        List<History> history = historyServiceImpl.findAll();
        List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
        model.addAttribute("partylots", partylots);
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("history", history);
        model.addAttribute("printers", printers);
        model.addAttribute("manufacturers",manufacturers);
        return "addCommentForPartylots";
    }

    @PostMapping("/addCommentsLook/{idHistory}/{lotNumber}/{idPartylots}")
    public String addCommentsForPartylots1(@PathVariable ("idHistory") long idHistory, @PathVariable ("lotNumber") String lotNumber,@PathVariable ("idPartylots") Long idPartylots,
                                           Partylots partylots){
        partyLotsServiceImpl.savePartylots(partylots);
        return "redirect:/view-lots/{lotNumber}/{idHistory}";
    }

    @GetMapping("/addCommentsCompare/{idHistory}/{idHistoryReturn}/{lotNumber}/{idPartylots}")
    public String addCommentsForPartylotsCompare(@PathVariable ("idHistory") long idHistory,@PathVariable("idHistoryReturn") long idHistoryReturn, @PathVariable ("lotNumber") String lotNumber,@PathVariable("idPartylots") long idPartylots, Model model) {
        Partylots partylots = partyLotsServiceImpl.findById(idPartylots);
        List<Cartridges> cartridges = cartridgeServiceImpl.findAll();
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        List<Printers> printers = printersServiceImpl.findAll();
        List<History> history = historyServiceImpl.findAll();
        List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
        model.addAttribute("partylots", partylots);
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("history", history);
        model.addAttribute("printers", printers);
        model.addAttribute("manufacturers",manufacturers);
        return "addCommentForPartylotsCompare";
    }

    @PostMapping("/addCommentsCompare/{idHistory}/{idHistoryReturn}/{lotNumber}/{idPartylots}")
    public String addCommentsForPartylotsCompare(@PathVariable ("idHistory") long idHistory,@PathVariable("idHistoryReturn") long idHistoryReturn,@PathVariable ("lotNumber") String lotNumber,@PathVariable ("idPartylots") Long idPartylots,
                                           Partylots partylots){
        partyLotsServiceImpl.savePartylots(partylots);
        return "redirect:/ComparisonPartyLots/{idHistory}/{idHistoryReturn}/{lotNumber}";
    }
}
