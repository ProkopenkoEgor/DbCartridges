package com.example.springbootsbyt.service;

import com.example.springbootsbyt.model.Cartridges;
import org.springframework.data.repository.query.Param;
import java.sql.Date;
import java.util.List;

public interface CartridgeService  {

    public Cartridges findById(Long id);

    public List<Cartridges> findAll();

    public Cartridges saveCartridge(Cartridges cartridge);

    public void deleteById(Long id);

    public List<Cartridges> findAll(String keyword);

    public Cartridges findByInventoryNumber(String inv);

    public List<Cartridges> findAllByPartyStatus0(@Param("date1") Date dt1,@Param("date2") Date dt2);

    public List<Cartridges> findAllByPartyStatus1And2(@Param("date1") Date dt1,@Param("date2") Date dt2);

    public List<Cartridges> findAllByPartyStatus3(@Param("date1") Date dt1, @Param("date2") Date dt2);

    public List<Cartridges> findAllByPartyStatus4And5(@Param("date1") Date dt1,@Param("date2") Date dt2);

    public List<Cartridges> findAllByPartyStatus(@Param("date1") Date dt1,@Param("date2") Date dt2);
}

