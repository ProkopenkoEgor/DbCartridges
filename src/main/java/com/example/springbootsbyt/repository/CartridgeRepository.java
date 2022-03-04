package com.example.springbootsbyt.repository;

import com.example.springbootsbyt.model.Cartridges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CartridgeRepository extends JpaRepository<Cartridges, Long> {
    @Query(value = "SELECT * from cartridges as c left join cartrs as ca on c.cartrs_id_cartrs = ca.id_cartrs" +
            " left join printers as p on ca.printers_id_printers=p.id_printers" +
            " where  c.city like :keyword or c.inventory_number like :keyword or c.count like :keyword" +
            " or ca.type_cartr like :keyword" +
            " or p.type_printers like :keyword", nativeQuery = true)
    List<Cartridges> findAll(@Param("keyword") String keyword);

    Cartridges findByInventoryNumber(String inv);

    @Query(value = "select * from cartridges as c left join partylots as p on c.id = p.cartridges_id left join history as h on h.id_history = p.history_id_history where p.party_status = 0 and h.date_of_status between ? and ?", nativeQuery = true)
    List<Cartridges> findAllByPartyStatus0(@Param("date1") Date dt1,@Param("date2") Date dt2);

    @Query(value = "select * from cartridges as c left join partylots as p on c.id = p.cartridges_id left join history as h on h.id_history = p.history_id_history where p.party_status in (1,2) and h.date_of_status between ? and ?", nativeQuery = true)
    List<Cartridges> findAllByPartyStatus1And2(@Param("date1") Date dt1,@Param("date2") Date dt2);

    @Query(value = "select * from cartridges as c left join partylots as p on c.id = p.cartridges_id left join history as h on h.id_history = p.history_id_history where p.party_status = 3 and h.date_of_status between ? and ?", nativeQuery = true)
    List<Cartridges> findAllByPartyStatus3(@Param("date1") Date dt1,@Param("date2") Date dt2);

    @Query(value = "select * from cartridges as c left join partylots as p on c.id = p.cartridges_id left join history as h on h.id_history = p.history_id_history where p.party_status in (4,5) and h.date_of_status between ? and ?", nativeQuery = true)
    List<Cartridges> findAllByPartyStatus4And5(@Param("date1") Date dt1,@Param("date2") Date dt2);

    @Query(value = "select * from cartridges as c left join partylots as p on c.id = p.cartridges_id left join history as h on h.id_history = p.history_id_history where p.party_status in (0,1,2,3,4,5) and h.date_of_status between ? and ? " +
            "group by p.cartridges_id order by  p.cartridges_id desc\n", nativeQuery = true)
    List<Cartridges> findAllByPartyStatus(@Param("date1") Date dt1,@Param("date2") Date dt2);
}