package com.example.springbootsbyt.service;

import com.example.springbootsbyt.model.History;

import java.util.List;

public interface HistoryService {

    public History findById(int id);

    public List<History> findAll();

    public History saveHistory(History history);

    public void deleteById(int id);


}
