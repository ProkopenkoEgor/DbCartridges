package com.example.springbootsbyt.service;

import com.example.springbootsbyt.model.Partylots;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartyLotsService {
    public Partylots findById(Long id);

    public List<Partylots> findAll();

    public Partylots savePartylots(Partylots partylots);

    public void deleteById(Long id);

    public List<Partylots> findAllByLotNumber(String pl);

    public List<Partylots> findAllByLotNumberAndCartridgesIdAndHistoryIdHistory(String k1,int k2, int k3);

    public List<Partylots> findDsLotNumber();

    public List<Partylots> findDsHistoryKeyword(long keyword);

    public List<Partylots> findDsHistory();

    public List<Partylots> findAllByPartyStatusNe0();

    public Partylots findOneByCartridgesId(long keyword,String keyword1);

    public Partylots findByCartridgesIdWherePartyStatus0(long keyword);

    public List<Partylots> findByCartridgesId(long keyword);

    public List<Partylots> findOneWherePartyStatus3Or4Or5(long keyword);

    public List<Partylots> findPartyLotsForDispose(String keyword);

    public Partylots findByCartridgesIdWherePartyStatus3Or4Or5(long keyword);
}
