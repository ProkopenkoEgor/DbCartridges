package com.example.springbootsbyt.controller;

import com.example.springbootsbyt.model.*;
import com.example.springbootsbyt.service.impl.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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
        return "partylotshistory";
    }
    @PostMapping("/lotsNumber/{idHistory}")
    public String CreateLotsNumber(@PathVariable ("idHistory") long idHistory,Partylots partylots){
        String str = partylots.getLotNumber();
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
        List<Partylots> partylots2 = partyLotsServiceImpl.findAll();
        List<History> histories = historyServiceImpl.findAll();
        model.addAttribute("partylots1",partylots1);
        model.addAttribute("partylots2",partylots2);
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
        List<Cartridges> cartridges = cartridgeServiceImpl.findAll();
        List<Partylots> partylots1 = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
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
    public String ComparisonPartyLots(@PathVariable ("idHistory") long idHistory,@PathVariable("idHistoryReturn") long idHistoryReturn,@PathVariable ("lotNumber") String lotNumber,Model model){
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

    @PostMapping("/Compare/{lotNumber}/{idHistory}/{idHistoryReturn}")
    public String ComparisonPartyLotsView(@PathVariable ("idHistory") long idHistory,@PathVariable("idHistoryReturn") long idHistoryReturn, @PathVariable ("lotNumber") String lotNumber,Partylots NewPartylots,BindingResult bindingResult,Model model){
        String barcode = Long.toString(NewPartylots.getCartridgesId());
        List<Partylots> partylotsEquals = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
        List<Cartridges> cartridges = cartridgeServiceImpl.findAllByInventoryNumber(barcode);
        if (cartridges.isEmpty()){
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
            model.addAttribute("partylots1",partylotsEquals);
            bindingResult.rejectValue("cartridgesId", "error.cartridgesId", "Такой картридж не существует");
            return "comparepartylots";
        }
        Cartridges cartridge = null;
        for (int i = 0; i < cartridges.size(); i++) {
            cartridge = cartridges.get(i);
            if (cartridge.getInventoryNumber().equals(barcode)) {
                Long beryID = cartridge.getId();
                NewPartylots.setCartridgesId(beryID);
            }
        }
        Partylots partylots1 = null;
        for (int i=0; i<partylotsEquals.size();i++){
            partylots1 = partylotsEquals.get(i);
            if (partylots1.getCartridgesId().equals(NewPartylots.getCartridgesId())){
                partylots1.setPartyStatus((long) 1);
                partylots1.setHistoryIdHistoryReturn(NewPartylots.getHistoryIdHistoryReturn());
            }
        }
        partyLotsServiceImpl.savePartylots(partylots1);
        return "redirect:/ComparisonPartyLots/{idHistory}/{idHistoryReturn}/{lotNumber}";
    }

    @PostMapping("/main/{idHistory}/{idHistoryReturn}/{lotNumber}")
    public String finishComparePartylots(@PathVariable ("idHistory") long idHistory,@PathVariable("idHistoryReturn") long idHistoryReturn, @PathVariable ("lotNumber") String lotNumber){
        List<Partylots> partylots = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
        Partylots partylots1 = null;
        for (int i=0;i<partylots.size();i++){
            partylots1 = partylots.get(i);
            if (partylots1.getPartyStatus()==1){
                partylots1.setPartyStatus((long) 2);
            }
        }
        partyLotsServiceImpl.savePartylots(partylots1);
        return "redirect:/main";
    }
}
