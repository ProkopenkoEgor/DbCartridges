package com.example.springbootsbyt.service.impl;

import com.example.springbootsbyt.model.Manufacturers;
import com.example.springbootsbyt.repository.ManufacturerRepository;
import com.example.springbootsbyt.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public Manufacturers findById(Integer id){
        return manufacturerRepository.findById(id).orElse(null);
    }

    public List<Manufacturers> findAll(){
        return manufacturerRepository.findAll();
    }

    public Manufacturers saveModels(Manufacturers manufacturers){
        return manufacturerRepository.save(manufacturers);
    }

    public void deleteById(Integer id){
        manufacturerRepository.deleteById(id);
    }

}
