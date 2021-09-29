package com.example.springbootsbyt.service;

import com.example.springbootsbyt.model.Cartridges;
import com.example.springbootsbyt.model.History;

import java.util.List;

public interface HistoryService {

    public History findById(Integer id);

    public List<History> findAll();

    public History saveHistory(History history);

    public History saveHistoryUpdate(History history);

    public void deleteById(Integer id, History history);

}
