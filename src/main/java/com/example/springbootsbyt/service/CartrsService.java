package com.example.springbootsbyt.service;

import com.example.springbootsbyt.model.Cartrs;

//import com.example.springbootsbyt.entity.Cartrs;

import java.util.List;

public interface CartrsService {

    public Cartrs findById(Integer id);

    public List<Cartrs> findAll();

    public Cartrs saveCartrs(Cartrs cartrs);

    public void deleteById(Integer id);

}
