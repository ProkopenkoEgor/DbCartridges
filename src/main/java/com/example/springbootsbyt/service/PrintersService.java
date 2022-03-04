package com.example.springbootsbyt.service;

import com.example.springbootsbyt.model.Printers;
import java.util.List;

public interface PrintersService {
    public Printers findById(Long id);

    public List<Printers> findAll();

    public Printers savePrinters(Printers printers);

    public void deleteById(Long id);

}
