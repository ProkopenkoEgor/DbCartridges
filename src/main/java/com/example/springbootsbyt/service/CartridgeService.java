package com.example.springbootsbyt.service;

import com.example.springbootsbyt.model.Cartridges;
import com.example.springbootsbyt.model.History;
import com.example.springbootsbyt.model.Partylots;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.sql.Date;
import java.util.List;

public interface CartridgeService  {

    public Cartridges findById(Long id);

    public List<Cartridges> findAll();

    public Cartridges saveCartridge(Cartridges cartridge);

    public void deleteById(Long id);

    public List<Cartridges> findAll(String keyword);

    public List<Cartridges> findAllByInventoryNumber(String barcode);

    public Cartridges findByInventoryNumber(String inv);
}

