package com.example.springbootsbyt.service.impl;

import com.example.springbootsbyt.model.Cartridges;
import com.example.springbootsbyt.repository.CartridgeRepository;
import com.example.springbootsbyt.service.CartridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;

@Service
public class CartridgeServiceImpl implements CartridgeService {

    private final CartridgeRepository cartridgeRepository;

    @Autowired
    public CartridgeServiceImpl(CartridgeRepository cartridgeRepository) {
        this.cartridgeRepository = cartridgeRepository;
    }

    public Cartridges findById(Long id){
        return cartridgeRepository.findById(id).orElse(null);
    }

    public List<Cartridges> findAll(){
        return cartridgeRepository.findAll();
    }

    public Cartridges saveCartridge(Cartridges cartridge){
        return cartridgeRepository.save(cartridge);
    }

    public void deleteById(Long id){
        cartridgeRepository.deleteById(id);
    }

    public List<Cartridges> findAll(String keyword){
        return cartridgeRepository.findAll(keyword);
    }

    public Cartridges findByInventoryNumber(String inv){
        return cartridgeRepository.findByInventoryNumber(inv);
    }

    @Override
    public List<Cartridges> findAllByPartyStatus0(Date dt1, Date dt2) {
        return cartridgeRepository.findAllByPartyStatus0(dt1,dt2);
    }

    @Override
    public List<Cartridges> findAllByPartyStatus1And2(Date dt1, Date dt2) {
        return cartridgeRepository.findAllByPartyStatus1And2(dt1,dt2);
    }

    public List<Cartridges> findAllByPartyStatus3(Date dt1, Date dt2) {
        return cartridgeRepository.findAllByPartyStatus3(dt1, dt2);
    }

    @Override
    public List<Cartridges> findAllByPartyStatus4And5(Date dt1, Date dt2) {
        return cartridgeRepository.findAllByPartyStatus4And5(dt1,dt2);
    }

    @Override
    public List<Cartridges> findAllByPartyStatus(Date dt1, Date dt2) {
        return cartridgeRepository.findAllByPartyStatus(dt1,dt2);
    }
}
