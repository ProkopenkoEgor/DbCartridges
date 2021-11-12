package com.example.springbootsbyt.service.impl;

import com.example.springbootsbyt.model.Partylots;
import com.example.springbootsbyt.repository.PartyLotsRepository;
import com.example.springbootsbyt.service.PartyLotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyLotsServiceImpl implements PartyLotsService {
    private final PartyLotsRepository partyLotsRepository;

    @Autowired
    public PartyLotsServiceImpl(PartyLotsRepository partyLotsRepository) {
        this.partyLotsRepository = partyLotsRepository;
    }

    @Override
    public Partylots findById(Integer id) {
        return partyLotsRepository.findById(id).orElse(null);
    }

    @Override
    public List<Partylots> findAll() {
        return partyLotsRepository.findAll();
    }

    @Override
    public Partylots savePartylots(Partylots partylots) {
        return partyLotsRepository.save(partylots);
    }

    @Override
    public void deleteById(Integer id) {
        partyLotsRepository.deleteById(id);
    }
}
