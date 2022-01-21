package com.example.springbootsbyt.controller;

import com.example.springbootsbyt.model.*;
import com.example.springbootsbyt.service.impl.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
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
        history.setDateOfStatus(Date.valueOf(LocalDate.now()));
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
        List<Partylots> partylots = partyLotsServiceImpl.findAllByLotNumber(NewPartylots.getLotNumber());
        Partylots partylots1 = null;
        for (int i = 0; i < partylots.size(); i++) {
            partylots1 = partylots.get(i);
            if (partylots1.getLotNumber().equals(NewPartylots.getLotNumber())) {
                History history = historyServiceImpl.findById(idHistory);
                model.addAttribute("history",history);
                NewPartylots.setLotNumber(null);
                bindingResult.rejectValue("lotNumber", "error.lotNumber", "Такой номер партии уже есть");
                return "partylotshistory";
            }
        }
        return "redirect:/CreatePartyLots/{idHistory}/" + NewPartylots.getLotNumber();
    }

    @GetMapping("/CreatePartyLots/{idHistory}/{lotNumber}")
    public String CreatePartyLots(@PathVariable ("idHistory") long idHistory,@PathVariable ("lotNumber") String lotNumber,
                                  @ModelAttribute("NewCartridge") Cartridges NewCartridge,Model model){
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
    public String CreatePartyLots(@PathVariable ("idHistory") long idHistory, @PathVariable ("lotNumber") String lotNumber,Partylots NewPartylots,
                                  @Valid
                                  @ModelAttribute("NewCartridge") Cartridges NewCartridge,BindingResult bindingResult,Model model){
        Cartridges cartridges = cartridgeServiceImpl.findByInventoryNumber(NewCartridge.getInventoryNumber());
        if (bindingResult.hasErrors()){
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
            return "partylotsfinal";
        }
        if (cartridges == null){
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
            NewCartridge.setInventoryNumber(null);
            bindingResult.rejectValue("inventoryNumber", "error.inventoryNumber", "Такой картридж не существует");
            return "partylotsfinal";
        }
        NewPartylots.setCartridgesId(cartridges.getId());
        List<Partylots> partylotsList = partyLotsServiceImpl.findAllByPartyStatusNe0();
        Partylots partylots11 = null;
        for (int i=0; i<partylotsList.size();i++) {
            partylots11 = partylotsList.get(i);
            if (NewPartylots.getCartridgesId().equals(partylots11.getCartridgesId())) {
                if (!partylots11.getLotNumber().equals(NewPartylots.getLotNumber())) {
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
                    NewCartridge.setInventoryNumber(null);
                    bindingResult.rejectValue("inventoryNumber", "error.inventoryNumber", "Такой картридж ещё не принят или уже находится в другой партии");
                    return "partylotsfinal";
                }
            }
        }
        List<Partylots> partylotsEquals = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
        Partylots partylots1 = null;
        for (int i=0; i<partylotsEquals.size();i++){
            partylots1 = partylotsEquals.get(i);
            if (partylots1.getCartridgesId().equals(NewPartylots.getCartridgesId())){
                List<Cartridges> cartridges1 = cartridgeServiceImpl.findAll();
                List<Partylots> partylots = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
                List<Cartrs> cartrs = cartrsServiceImpl.findAll();
                History history = historyServiceImpl.findById(idHistory);
                List<Printers> printers = printersServiceImpl.findAll();
                List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
                model.addAttribute("cartridges", cartridges1);
                model.addAttribute("cartrs", cartrs);
                model.addAttribute("history", history);
                model.addAttribute("printers", printers);
                model.addAttribute("manufacturers",manufacturers);
                model.addAttribute("partylots1",partylots);
                NewCartridge.setInventoryNumber(null);
                bindingResult.rejectValue("inventoryNumber", "error.inventoryNumber", "Такой картридж уже внесён");
                return "partylotsfinal";
            }
        }
        History history = historyServiceImpl.findById(idHistory);
        if (history.getStatus().equalsIgnoreCase("на списание")){
            NewPartylots.setPartyStatus((long) 3);
            partyLotsServiceImpl.savePartylots(NewPartylots);
            return "redirect:/CreatePartyLots/{idHistory}/{lotNumber}";
        }
        List<Partylots> partylots3or4or5 = partyLotsServiceImpl.findOneWherePartyStatus3Or4Or5(NewPartylots.getCartridgesId());
        if (!partylots3or4or5.isEmpty()){
            List<Cartridges> cartridges1 = cartridgeServiceImpl.findAll();
            List<Partylots> partylots = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            model.addAttribute("cartridges", cartridges1);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("history", history);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("history", history);
            model.addAttribute("partylots1",partylots);
            NewCartridge.setInventoryNumber(null);
            bindingResult.rejectValue("inventoryNumber", "error.inventoryNumber", "Такой картридж находится на списании или уже утилизирован");
            return "partylotsfinal";
        }
        partyLotsServiceImpl.savePartylots(NewPartylots);
        return "redirect:/CreatePartyLots/{idHistory}/{lotNumber}";
    }

    @PostMapping("/main/{lotNumber}/{idHistory}")
    public String sendPartyLots(@PathVariable("lotNumber") String lotNumber,@PathVariable("idHistory") long idHistory){
        List<Partylots> partylotsList = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
        List<Cartridges> cartridgesList = cartridgeServiceImpl.findAll();
        History history = historyServiceImpl.findById(idHistory);
        Partylots partylots1 = null;
        Cartridges cartridges1 = null;
        if (history.getStatus().equalsIgnoreCase("в заправке")) {
        for (int i=0; i<partylotsList.size();i++) {
            partylots1 = partylotsList.get(i);
            for (int j = 0; j < cartridgesList.size(); j++) {
                cartridges1 = cartridgesList.get(j);
                if (partylots1.getCartridgesId().equals(cartridges1.getId())) {
                    cartridges1.setCount(cartridges1.getCount() + 1);
                    cartridgeServiceImpl.saveCartridge(cartridges1);
                    }
                }
            }
        }
        return "redirect:/main";
    }

    @GetMapping("/lots-delete/{idPartylots}/{idHistory}/{lotNumber}")
    public String deletePartyLots(@PathVariable("idPartylots") long idPartylots,@PathVariable ("idHistory") int idHistory,@PathVariable ("lotNumber") String lotNumber) {
        partyLotsServiceImpl.deleteById(idPartylots);
        return "redirect:/CreatePartyLots/{idHistory}/{lotNumber}";
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
        return "partylots-list";
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
        List<Cartridges> cartridges = cartridgeServiceImpl.findAll();
        List<Cartrs> cartrs = cartrsServiceImpl.findAll();
        History history = historyServiceImpl.findById(idHistory);
        History historyReturn = new History();
        historyReturn.setDateOfStatus(Date.valueOf(LocalDate.now()));
        List<Printers> printers = printersServiceImpl.findAll();
        List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
        model.addAttribute("cartridges", cartridges);
        model.addAttribute("cartrs", cartrs);
        model.addAttribute("history", history);
        model.addAttribute("history1",historyReturn);
        model.addAttribute("printers", printers);
        model.addAttribute("manufacturers",manufacturers);
        model.addAttribute("partylots1",partylots1);
        model.addAttribute("partylots",new Partylots());
        List<Partylots> partylotsDispose = partyLotsServiceImpl.findPartyLotsForDispose(lotNumber);
        if (!partylotsDispose.isEmpty()){
            historyReturn.setStatus("Утилизация");
            historyReturn.setExecutor("Пользователь");
            historyServiceImpl.saveHistory(historyReturn);
            return "redirect:/ComparisonPartyLots/{idHistory}/" + historyReturn.getIdHistory() + "/{lotNumber}";
        }
        historyReturn.setStatus("в работе");
        historyReturn.setExecutor("ОАСУ");
        historyServiceImpl.saveHistory(historyReturn);
        return "redirect:/ComparisonPartyLots/{idHistory}/" + historyReturn.getIdHistory() + "/{lotNumber}";
    }

    @GetMapping("/ComparisonPartyLots/{idHistory}/{idHistoryReturn}/{lotNumber}")
    public String ComparisonPartyLots(@PathVariable ("idHistory") long idHistory, @PathVariable("idHistoryReturn") long idHistoryReturn, @PathVariable ("lotNumber") String lotNumber, Model model,
                                      @Param("inventoryNumber") String inventoryNumber){
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
                                      @Param("inventoryNumber") String inventoryNumber){
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
                                          Partylots NewPartylots, @Param("inventoryNumber") String inventoryNumber,Model model){
        List<Partylots> partylotsEquals = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
        String str = StringUtils.trim(inventoryNumber);
        if (str.isEmpty()){
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
            model.addAttribute("response","Empty");
            return "comparepartylots";
        }
        Cartridges cartridges11 = cartridgeServiceImpl.findByInventoryNumber(str);
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
            model.addAttribute("response","Empty1");
            return "comparepartylots";
        }
        Partylots partylots1 = null;
        for (int i=0; i<partylotsEquals.size();i++) {
            partylots1 = partylotsEquals.get(i);
            if (partylots1.getCartridgesId() == null) {
                continue;
            }
            if (partylots1.getCartridgesId().equals(NewPartylots.getCartridgesId())){
                if (partylots1.getPartyStatus() == 1) {
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
                    model.addAttribute("response","Empty2");
                    return "comparepartylots";
                }
                if (partylots1.getPartyStatus() == 3){
                    partylots1.setPartyStatus((long) 4);
                    partylots1.setHistoryIdHistoryReturn(NewPartylots.getHistoryIdHistoryReturn());
                    partyLotsServiceImpl.savePartylots(partylots1);
                    return "redirect:/ComparisonPartyLotsLookForCheck/{idHistory}/{idHistoryReturn}/{lotNumber}/" + partylots1.getIdPartylots();
                }
                if (partylots1.getPartyStatus() == 4){
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
                    model.addAttribute("response","Empty2");
                    return "comparepartylots";
                }
                partylots1.setPartyStatus((long) 1);
                partylots1.setHistoryIdHistoryReturn(NewPartylots.getHistoryIdHistoryReturn());
                partyLotsServiceImpl.savePartylots(partylots1);
                return "redirect:/ComparisonPartyLotsLookForCheck/{idHistory}/{idHistoryReturn}/{lotNumber}/" + partylots1.getIdPartylots();
            }
        }
        Partylots NeTaPartylots = partyLotsServiceImpl.findOneByCartridgesId(NewPartylots.getCartridgesId(),NewPartylots.getLotNumber());
        if (NeTaPartylots != null){
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
            NeTaPartylots.setPartyStatus((long) 1);
            NeTaPartylots.setHistoryIdHistoryReturn(NewPartylots.getHistoryIdHistoryReturn());
            partyLotsServiceImpl.savePartylots(NeTaPartylots);
            Partylots partylotsOne = partyLotsServiceImpl.findById(NeTaPartylots.getIdPartylots());
            model.addAttribute("response","Empty3");
            model.addAttribute("partylotsOne",partylotsOne);
            return "comparepartylots";
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
            model.addAttribute("response","Empty4");
            return "comparepartylots";
        }
    }

    @PostMapping("/main/{idHistory}/{idHistoryReturn}/{lotNumber}")
    public String finishComparePartylots(@PathVariable ("idHistory") long idHistory,@PathVariable("idHistoryReturn") long idHistoryReturn, @PathVariable ("lotNumber") String lotNumber,
                                         @Param("inventoryNumber") String inventoryNumber,Model model){
        List<Partylots> partylots = partyLotsServiceImpl.findAllByLotNumber(lotNumber);
        Partylots partylots2 = null;
        for (int i=0;i<partylots.size();i++){
            partylots2 = partylots.get(i);
            if (partylots2.getPartyStatus()==1){
                partylots2.setPartyStatus((long) 2);
            }
            if (partylots2.getPartyStatus() == 0) {
                if (partylots2.getCartridgesId() == null){
                    continue;
                }
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
                model.addAttribute("partylots",new Partylots());
                model.addAttribute("response","Empty5");
                return "comparepartylots";
            }
            if (partylots2.getPartyStatus() == 4){
                    partylots2.setPartyStatus((long) 5);
            }
            if (partylots2.getPartyStatus() == 3){
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
                model.addAttribute("partylots",new Partylots());
                model.addAttribute("response","Empty6");
                return "comparepartylots";
            }
        }
        partyLotsServiceImpl.savePartylots(partylots2);
        return "redirect:/main";
    }

    @GetMapping("/lots-check/{idPartylots}/{idHistory}/{idHistoryReturn}/{lotNumber}")
    public String checkOnePartyLots(@PathVariable("idPartylots") long idPartylots,@PathVariable ("idHistory") long idHistory,@PathVariable("idHistoryReturn") long idHistoryReturn,
                                    @PathVariable ("lotNumber") String lotNumber){
        Partylots partylots = partyLotsServiceImpl.findById(idPartylots);
        if (partylots.getPartyStatus() == 3){
            partylots.setPartyStatus((long) 4);
            partylots.setHistoryIdHistoryReturn(idHistoryReturn);
            partyLotsServiceImpl.savePartylots(partylots);
            return "redirect:/ComparisonPartyLots/{idHistory}/{idHistoryReturn}/{lotNumber}";
        }
        partylots.setPartyStatus((long) 1);
        partylots.setHistoryIdHistoryReturn(idHistoryReturn);
        partyLotsServiceImpl.savePartylots(partylots);
        return "redirect:/ComparisonPartyLots/{idHistory}/{idHistoryReturn}/{lotNumber}";
    }

    @GetMapping("/lots-check-return/{idPartylots}/{idHistory}/{idHistoryReturn}/{lotNumber}")
    public String checkOnePartyLotsReturn(@PathVariable("idPartylots") long idPartylots,@PathVariable ("idHistory") long idHistory,
                                          @PathVariable("idHistoryReturn") long idHistoryReturn, @PathVariable ("lotNumber") String lotNumber){
        Partylots partylots = partyLotsServiceImpl.findById(idPartylots);
        if (partylots.getPartyStatus() == 4){
            partylots.setPartyStatus((long) 3);
            partylots.setHistoryIdHistoryReturn(null);
            partyLotsServiceImpl.savePartylots(partylots);
            return "redirect:/ComparisonPartyLots/{idHistory}/{idHistoryReturn}/{lotNumber}";
        }
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

    @GetMapping("/searchPartylotsByCartridge")
    public String findByCartridgeOnePartylots(@ModelAttribute("NewCartridge") Cartridges NewCartridge){
        return "searchPartylotsByCartridge";
    }

    @PostMapping("/SearchFromPartylotsByOne")
    public String searchFromPartylotsByOne(@Valid @ModelAttribute("NewCartridge") Cartridges NewCartridge,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "searchPartylotsByCartridge";
        }
        Cartridges cartridges = cartridgeServiceImpl.findByInventoryNumber(NewCartridge.getInventoryNumber());
        if (cartridges != null) {
            Partylots partylots = partyLotsServiceImpl.findByCartridgesIdWherePartyStatus0Or1(cartridges.getId());
            if (partylots != null) {
                return "redirect:/ComparePartyLots/" + partylots.getHistoryIdHistory() + "/" + partylots.getLotNumber();
            } else {
                Partylots partylots1 = partyLotsServiceImpl.findByCartridgesIdWherePartyStatus3Or4Or5(cartridges.getId());
                if (partylots1 != null){
                    return "redirect:/ComparePartyLots/" + partylots1.getHistoryIdHistory() + "/" + partylots1.getLotNumber();
                }
                List<Partylots> partylots2 = partyLotsServiceImpl.findByCartridgesId(cartridges.getId());
                if (partylots2.isEmpty()){
                    bindingResult.rejectValue("inventoryNumber", "error.inventoryNumber", "Такой картридж ещё не был использован в партиях");
                    return "searchPartylotsByCartridge";
                }
                bindingResult.rejectValue("inventoryNumber", "error.inventoryNumber", "Такой картридж находится уже в закрытой партии");
                return "searchPartylotsByCartridge";
            }
        } else {
            bindingResult.rejectValue("inventoryNumber", "error.inventoryNumber", "Такой картридж не существует");
            return "searchPartylotsByCartridge";
        }
    }

    @GetMapping("/deleteOneCartridgeFromPartyLots/{cartridgesId}/{lotNumber}/{idHistory}")
    public String deleteOneCartridgeFromPartyLots(@PathVariable("cartridgesId") long cartridgesId,@PathVariable("lotNumber") String lotNumber,@PathVariable("idHistory") long idHistory){
        Partylots partylots = partyLotsServiceImpl.findByCartridgesIdWhereLotNumberRavnoLotNumber(cartridgesId,lotNumber);
        partylots.setCartridgesId(null);
        partylots.setHistoryIdHistory(null);
        partylots.setPartyComments(null);
        partylots.setPartyStatus((long) 0);
        partyLotsServiceImpl.savePartylots(partylots);
        return "redirect:/view-lots/{lotNumber}/{idHistory}";
    }
}