package com.example.springbootsbyt.service;

import com.example.springbootsbyt.model.Partylots;
import com.example.springbootsbyt.model.Printers;

import java.util.List;

public interface PartyLotsService {
    public Partylots findById(Integer id);

    public List<Partylots> findAll();

    public Partylots savePartylots(Partylots partylots);

    public void deleteById(Integer id);
}
