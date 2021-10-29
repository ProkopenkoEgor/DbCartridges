package com.example.springbootsbyt.service;

import com.example.springbootsbyt.model.Manufacturers;

import java.util.List;

public interface ManufacturerService {
    public Manufacturers findById(Integer id);

    public List<Manufacturers> findAll();

    public Manufacturers saveModels(Manufacturers manufacturers);

    public void deleteById(Integer id);
}
