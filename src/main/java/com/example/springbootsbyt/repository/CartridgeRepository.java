package com.example.springbootsbyt.repository;

//import com.example.springbootsbyt.entity.Cartridges;
import com.example.springbootsbyt.model.Cartridges;
import com.example.springbootsbyt.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Repository
public interface CartridgeRepository extends JpaRepository<Cartridges, Integer> {
    @Query(value="SELECT * from cartridges as c left join cartrs as ca on c.cartrs_id_cartrs = ca.id_cartrs" +
            " left join printers as p on ca.printers_id_printers=p.id_printers" +
            " where  c.city like :keyword or c.inventory_number like :keyword or c.count like :keyword" +
            " or ca.type_cartr like :keyword" +
            " or p.type_printers like :keyword", nativeQuery = true)
    List<Cartridges> findAll(@Param("keyword") String keyword);
    }
