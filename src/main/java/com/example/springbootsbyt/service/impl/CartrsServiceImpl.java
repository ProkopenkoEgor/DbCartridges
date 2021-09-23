package com.example.springbootsbyt.service.impl;

import com.example.springbootsbyt.model.Cartrs;
//import com.example.springbootsbyt.entity.Cartrs;
import com.example.springbootsbyt.repository.CartrsRepository;
import com.example.springbootsbyt.service.CartrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartrsServiceImpl implements CartrsService {

    private final CartrsRepository cartrsRepository;

    @Autowired
    public CartrsServiceImpl(CartrsRepository cartrsRepository) {
        this.cartrsRepository = cartrsRepository;
    }

    public Cartrs findById(int id){
        return cartrsRepository.findById(id).orElse(null);
    }
    public List<Cartrs> findAll(){
        return cartrsRepository.findAll();
    }
    public Cartrs saveCartrs(Cartrs cartrs){
        return cartrsRepository.save(cartrs);
    }

    public void deleteById(int id){
        cartrsRepository.deleteById(id);
    }
}
