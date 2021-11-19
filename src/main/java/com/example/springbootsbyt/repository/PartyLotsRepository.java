package com.example.springbootsbyt.repository;

import com.example.springbootsbyt.model.Partylots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyLotsRepository extends JpaRepository<Partylots, Integer> {
    List<Partylots> findAllByLotNumber(String pl);

    List<Partylots> findAllByLotNumberAndCartridgesIdAndHistoryIdHistory(String k1,int k2, int k3);

    @Query(value="select * from partylots group by lot_number",nativeQuery=true)
    List<Partylots> findDsLotNumber();

    @Query(value="select * from partylots where history_id_history like :keyword group by lot_number",nativeQuery=true)
    List<Partylots> findDsHistoryKeyword(@Param("keyword") int keyword);

    @Query(value="select * from partylots group by history_id_history",nativeQuery=true)
    List<Partylots> findDsHistory();
}

