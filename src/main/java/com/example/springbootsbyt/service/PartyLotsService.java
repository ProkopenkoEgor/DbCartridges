package com.example.springbootsbyt.service;

import com.example.springbootsbyt.model.Cartridges;
import com.example.springbootsbyt.model.Partylots;
import com.example.springbootsbyt.model.Printers;

import java.util.List;

public interface PartyLotsService {
    public Partylots findById(Integer id);

    public List<Partylots> findAll();

    public Partylots savePartylots(Partylots partylots);

    public void deleteById(Integer id);

    public List<Partylots> findAllByLotNumber(String pl);

    public List<Partylots> findAllByLotNumberAndCartridgesIdAndHistoryIdHistory(String k1,int k2, int k3);

    public List<Partylots> findDsLotNumber();

    public List<Partylots> findDsHistoryKeyword(int keyword);

    public List<Partylots> findDsHistory();
}
