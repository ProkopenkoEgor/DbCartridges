package com.example.springbootsbyt.service;

import com.example.springbootsbyt.model.Cartrs;
import java.util.List;

public interface CartrsService {

    public Cartrs findById(Long id);

    public List<Cartrs> findAll();

    public Cartrs saveCartrs(Cartrs cartrs);

    public void deleteById(Long id);

}
