package com.example.springbootsbyt.service;

import com.example.springbootsbyt.model.Cartridges;
import com.example.springbootsbyt.model.History;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.sql.Date;
import java.util.List;

public interface CartridgeService  {

    public Cartridges findById(Integer id);

    public List<Cartridges> findAll();

    public Cartridges saveCartridge(Cartridges cartridge);

    public void deleteById(Integer id);

    public List<Cartridges> findAll(String keyword);

}

