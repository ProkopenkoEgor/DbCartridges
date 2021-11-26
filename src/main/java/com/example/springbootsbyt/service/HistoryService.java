package com.example.springbootsbyt.service;

import com.example.springbootsbyt.model.Cartridges;
import com.example.springbootsbyt.model.History;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface HistoryService {

    public History findById(Long id);

    public List<History> findAll();

    public History saveHistory(History history);

    public History saveHistoryUpdate(History history);

    public void deleteById(Long id, History history);

    public List<History> findByDateOfStatus(@Param("Date") Date Date);
}
