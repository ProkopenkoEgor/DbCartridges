package com.example.springbootsbyt.service.impl;

import com.example.springbootsbyt.model.Cartridges;
//import com.example.springbootsbyt.entity.Cartridges;
import com.example.springbootsbyt.model.Cartrs;
import com.example.springbootsbyt.repository.CartridgeRepository;
import com.example.springbootsbyt.service.CartridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CartridgeServiceImpl implements CartridgeService {

    private final CartridgeRepository cartridgeRepository;

    @Autowired
    public CartridgeServiceImpl(CartridgeRepository cartridgeRepository) {
        this.cartridgeRepository = cartridgeRepository;
    }

    public Cartridges findById(int id){
        return cartridgeRepository.findById(id).orElse(null);
    }

    public List<Cartridges> findAll(){
        return cartridgeRepository.findAll();
    }

    public Cartridges saveCartridge(Cartridges cartridge){
        return cartridgeRepository.save(cartridge);
    }

    public void deleteById(int id){
        cartridgeRepository.deleteById(id);
    }



}
