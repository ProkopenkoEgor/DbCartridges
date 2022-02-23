package com.example.springbootsbyt.service;

import com.example.springbootsbyt.model.History;
import org.springframework.data.repository.query.Param;
import java.sql.Date;
import java.util.List;

public interface HistoryService {

    public History findById(Long id);

    public List<History> findAll();

    public History saveHistory(History history);

    public History saveHistoryUpdate(History history);

    public void deleteById(Long id, History history);

    public List<History> findByDateOfStatus(@Param("Date") Date Date);

    public List<History> findByDateOfStatusBetweenDate1AndDate2(@Param("date1") Date dt1, @Param("date2") Date dt2);
}
