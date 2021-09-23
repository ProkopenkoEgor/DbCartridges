package com.example.springbootsbyt.service;

import com.example.springbootsbyt.model.Cartridges;


import java.util.List;

public interface CartridgeService {

    public Cartridges findById(int id);

    public List<Cartridges> findAll();

    public Cartridges saveCartridge(Cartridges cartridge);

    public void deleteById(int id);



}

