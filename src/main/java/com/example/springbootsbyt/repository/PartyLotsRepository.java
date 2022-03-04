package com.example.springbootsbyt.repository;

import com.example.springbootsbyt.model.Partylots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PartyLotsRepository extends JpaRepository<Partylots, Long> {
    List<Partylots> findAllByLotNumber(String pl);

    List<Partylots> findAllByLotNumberAndCartridgesIdAndHistoryIdHistory(String k1,int k2, int k3);

    @Query(value="select * from partylots where cartridges_id is not null and history_id_history is not null group by lot_number",nativeQuery=true)
    List<Partylots> findDsLotNumber();

    @Query(value="select * from partylots where history_id_history like :keyword group by lot_number",nativeQuery=true)
    List<Partylots> findDsHistoryKeyword(@Param("keyword") long keyword);

    @Query(value="select * from partylots group by history_id_history",nativeQuery=true)
    List<Partylots> findDsHistory();

    @Query(value="select * from partylots where party_status = 0",nativeQuery=true)
    List<Partylots> findAllByPartyStatusNe0();

    @Query(value="select * from partylots where party_status = 0 and cartridges_id like :keyword and lot_number not like :keyword1",nativeQuery=true)
    Partylots findOneByCartridgesId(@Param("keyword") long keyword, @Param("keyword1") String keyword1);

    @Query(value="SELECT * from partylots where party_status in (0,1) and cartridges_id like :keyword",nativeQuery=true)
    Partylots findByCartridgesIdWherePartyStatus0Or1(@Param("keyword") long keyword);

    @Query(value="select * from partylots where cartridges_id like :keyword", nativeQuery=true)
    List<Partylots> findByCartridgesId(@Param("keyword") long keyword);

    @Query(value="select * from partylots where cartridges_id like :keyword and party_status in (3,4,5)",nativeQuery=true)
    List<Partylots> findOneWherePartyStatus3Or4Or5(@Param("keyword") long keyword);

    @Query(value="select * from partylots where lot_number like :keyword and party_status in (3,4)",nativeQuery=true)
    List<Partylots> findPartyLotsForDispose(@Param("keyword") String keyword);

    @Query(value="select * from partylots where cartridges_id like :keyword and party_status in (3,4,5)",nativeQuery=true)
    Partylots findByCartridgesIdWherePartyStatus3Or4Or5(@Param("keyword") long keyword);

    @Query(value="select * from partylots where cartridges_id like :keyword and lot_number like :keyword1",nativeQuery=true)
    Partylots findByCartridgesIdWhereLotNumberRavnoLotNumber(@Param("keyword") long keyword, @Param("keyword1") String keyword1);

    @Query(value="select * from partylots where cartridges_id is not null and history_id_history is not null and party_status in (0,1) group by lot_number",nativeQuery=true)
    List<Partylots> findDsLotNumberByPartyStatus0Or1();

    @Query(value="select * from partylots where cartridges_id is not null and history_id_history is not null and party_status = 2 group by lot_number",nativeQuery=true)
    List<Partylots> findDsLotNumberByPartyStatus2();

    @Query(value="select * from partylots where cartridges_id is not null and history_id_history is not null and party_status in (3,4) group by lot_number",nativeQuery=true)
    List<Partylots> findDsLotNumberByPartyStatus3Or4();

    @Query(value="select * from partylots where cartridges_id is not null and history_id_history is not null and party_status = 5 group by lot_number",nativeQuery=true)
    List<Partylots> findDsLotNumberByPartyStatus5();

    @Query(value="select * from partylots group by cartridges_id order by  cartridges_id desc",nativeQuery=true)
    List<Partylots> findDsCartridgesId();
}

