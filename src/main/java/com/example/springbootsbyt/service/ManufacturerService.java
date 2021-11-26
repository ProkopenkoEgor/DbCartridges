package com.example.springbootsbyt.service;

import com.example.springbootsbyt.model.Manufacturers;

import java.util.List;

public interface ManufacturerService {
    public Manufacturers findById(Long id);

    public List<Manufacturers> findAll();

    public Manufacturers saveModels(Manufacturers manufacturers);

    public void deleteById(Long id);
}
