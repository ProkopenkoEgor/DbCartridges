package com.example.springbootsbyt.service.impl;

import com.example.springbootsbyt.model.Cartridges;
//import com.example.springbootsbyt.entity.Cartridges;
import com.example.springbootsbyt.model.History;
import com.example.springbootsbyt.repository.CartridgeRepository;
import com.example.springbootsbyt.service.CartridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartridgeServiceImpl implements CartridgeService {

    private final CartridgeRepository cartridgeRepository;

    @Autowired
    public CartridgeServiceImpl(CartridgeRepository cartridgeRepository) {
        this.cartridgeRepository = cartridgeRepository;
    }

    public Cartridges findById(Integer id){
        return cartridgeRepository.findById(id).orElse(null);
    }

    public List<Cartridges> findAll(){
        return cartridgeRepository.findAll();
    }

    public Cartridges saveCartridge(Cartridges cartridge){
        return cartridgeRepository.save(cartridge);
    }

    public void deleteById(Integer id){
        cartridgeRepository.deleteById(id);
    }


}