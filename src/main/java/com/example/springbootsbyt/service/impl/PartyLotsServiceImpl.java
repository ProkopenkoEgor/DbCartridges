package com.example.springbootsbyt.service.impl;

import com.example.springbootsbyt.model.Cartridges;
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

    public List<Partylots> findAllByLotNumber(String pl){
        return partyLotsRepository.findAllByLotNumber(pl);
    }
    public  List<Partylots> findAllByLotNumberAndCartridgesIdAndHistoryIdHistory(String k1,int k2, int k3){
        return partyLotsRepository.findAllByLotNumberAndCartridgesIdAndHistoryIdHistory(k1,k2,k3);
    }

    public List<Partylots> findDsLotNumber(){
        return partyLotsRepository.findDsLotNumber();
    }

    public List<Partylots> findDsHistoryKeyword(int keyword){
        return partyLotsRepository.findDsHistoryKeyword(keyword);
    }

    public List<Partylots> findDsHistory(){
        return partyLotsRepository.findDsHistory();
    }


}
