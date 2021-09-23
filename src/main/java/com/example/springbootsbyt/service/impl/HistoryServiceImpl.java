package com.example.springbootsbyt.service.impl;

import com.example.springbootsbyt.model.History;
import com.example.springbootsbyt.repository.HistoryRepository;
import com.example.springbootsbyt.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public History findById(int id) {
        return historyRepository.findById(id).orElse(null);
    }

    public List<History> findAll() {
        return historyRepository.findAll();
    }

    public History saveHistory(History history) {
        return historyRepository.save(history);
    }

    public void deleteById(int id) {
        historyRepository.deleteById(id);
    }
}
