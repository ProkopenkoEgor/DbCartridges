package com.example.springbootsbyt.service;

import com.example.springbootsbyt.model.Cartridges;
import com.example.springbootsbyt.model.Printers;

import java.util.List;
import java.util.Set;

public interface PrintersService {
    public Printers findById(Integer id);

    public List<Printers> findAll();

    public Printers savePrinters(Printers printers);

    public void deleteById(Integer id);
}
