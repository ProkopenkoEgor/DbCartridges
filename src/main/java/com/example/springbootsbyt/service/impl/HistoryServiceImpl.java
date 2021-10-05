package com.example.springbootsbyt.service.impl;

import com.example.springbootsbyt.model.Cartridges;
import com.example.springbootsbyt.model.History;
import com.example.springbootsbyt.repository.CartridgeRepository;
import com.example.springbootsbyt.repository.HistoryRepository;
import com.example.springbootsbyt.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final CartridgeRepository cartridgeRepository;
    private final CartridgeServiceImpl cartridgeServiceImpl;

    @Autowired
    public HistoryServiceImpl(HistoryRepository historyRepository, CartridgeRepository cartridgeRepository, CartridgeServiceImpl cartridgeServiceImpl) {
        this.historyRepository = historyRepository;
        this.cartridgeRepository = cartridgeRepository;
        this.cartridgeServiceImpl = cartridgeServiceImpl;
    }

    public History findById(Integer id) {
        return historyRepository.findById(id).orElse(null);
    }

    public List<History> findAll() {
        return historyRepository.findAll();
    }

    public History saveHistory(History history) {
        String str3 = history.getStatus();
        Cartridges cartridge = null;
        List<Cartridges> cartridges1 = cartridgeServiceImpl.findAll();
        for (int i = 0; i < cartridges1.size(); i++) {
            cartridge = cartridges1.get(i);
            if (cartridge.getId() == history.getCartridgesId()) {
                if (str3.equalsIgnoreCase("в заправке") == true) {
                    Integer count = cartridge.getCount();
                    count++;
                    cartridge.setCount(count);
                    cartridgeServiceImpl.saveCartridge(cartridge);
                }
            }
        }
        return historyRepository.save(history);
    }

    public void deleteById(Integer id,History history) {
        String str4 = history.getStatus();
        Cartridges cartridge = null;
        List<Cartridges> cartridges1 = cartridgeServiceImpl.findAll();
        for (int i = 0; i < cartridges1.size(); i++) {
            cartridge = cartridges1.get(i);
            if (cartridge.getId() == history.getCartridgesId()) {
                if (str4.equalsIgnoreCase("в заправке") == true) {
                    Integer count = cartridge.getCount();
                    count--;
                    cartridge.setCount(count);
                    cartridgeServiceImpl.saveCartridge(cartridge);
                }
            }
        }
        historyRepository.deleteById(id);
    }

    public History saveHistoryUpdate(History history1) {
        String str3 = history1.getStatus();
        Cartridges cartridge = null;
        List<Cartridges> cartridges1 = cartridgeServiceImpl.findAll();
        for (int i = 0; i < cartridges1.size(); i++) {
            cartridge = cartridges1.get(i);
            if (cartridge.getId() == history1.getCartridgesId()) {
                if (str3.equalsIgnoreCase("в заправке") == true) {
                    Integer count = cartridge.getCount();
                    count++;
                    cartridge.setCount(count);
                    cartridgeServiceImpl.saveCartridge(cartridge);
                }
                else {
                    Integer count = cartridge.getCount();
                    count--;
                    cartridge.setCount(count);
                    cartridgeServiceImpl.saveCartridge(cartridge);
                }
            }
        }
        return historyRepository.save(history1);
    }
    public List<History> findByDateOfStatus(@Param("Date") Date Date){
        return historyRepository.findByDateOfStatus(Date);
    }
}
