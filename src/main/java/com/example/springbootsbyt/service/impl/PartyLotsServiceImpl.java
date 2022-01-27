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
    public Partylots findById(Long id) {
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
    public void deleteById(Long id) {
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

    public List<Partylots> findDsHistoryKeyword(long keyword){
        return partyLotsRepository.findDsHistoryKeyword(keyword);
    }

    public List<Partylots> findDsHistory(){
        return partyLotsRepository.findDsHistory();
    }

    public List<Partylots> findAllByPartyStatusNe0(){
        return partyLotsRepository.findAllByPartyStatusNe0();
    }

    public Partylots findOneByCartridgesId(long keyword, String keyword1){
        return partyLotsRepository.findOneByCartridgesId(keyword,keyword1);
    }

    public Partylots findByCartridgesIdWherePartyStatus0Or1(long keyword){
        return partyLotsRepository.findByCartridgesIdWherePartyStatus0Or1(keyword);
    }

    public List<Partylots> findByCartridgesId(long keyword) {
        return partyLotsRepository.findByCartridgesId(keyword);
    }

    public List<Partylots> findOneWherePartyStatus3Or4Or5(long keyword){
        return partyLotsRepository.findOneWherePartyStatus3Or4Or5(keyword);
    }

    public List<Partylots> findPartyLotsForDispose(String keyword){
        return partyLotsRepository.findPartyLotsForDispose(keyword);
    }

    public Partylots findByCartridgesIdWherePartyStatus3Or4Or5(long keyword){
        return partyLotsRepository.findByCartridgesIdWherePartyStatus3Or4Or5(keyword);
    }

    public Partylots findByCartridgesIdWhereLotNumberRavnoLotNumber(long keyword, String keyword1){
        return partyLotsRepository.findByCartridgesIdWhereLotNumberRavnoLotNumber(keyword,keyword1);
    }

    @Override
    public List<Partylots> findDsLotNumberByPartyStatus0Or1() {
        return partyLotsRepository.findDsLotNumberByPartyStatus0Or1();
    }

    @Override
    public List<Partylots> findDsLotNumberByPartyStatus2() {
        return partyLotsRepository.findDsLotNumberByPartyStatus2();
    }

    @Override
    public List<Partylots> findDsLotNumberByPartyStatus3Or4() {
        return partyLotsRepository.findDsLotNumberByPartyStatus3Or4();
    }

    @Override
    public List<Partylots> findDsLotNumberByPartyStatus5() {
        return partyLotsRepository.findDsLotNumberByPartyStatus5();
    }

    public List<Partylots> findDsCartridgesId() {
        return partyLotsRepository.findDsCartridgesId();
    }
}
