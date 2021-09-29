package com.example.springbootsbyt.service;

import com.example.springbootsbyt.model.Cartridges;


import java.util.List;

public interface CartridgeService  {

    public Cartridges findById(Integer id);

    public List<Cartridges> findAll();

    public Cartridges saveCartridge(Cartridges cartridge);

    public void deleteById(Integer id);

}

