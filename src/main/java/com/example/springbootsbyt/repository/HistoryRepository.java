package com.example.springbootsbyt.repository;

import com.example.springbootsbyt.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query(value="select * from history as h left join cartridges as c on h.cartridges_id = c.id where h.date_of_status like :Date order by c.inventory_number",nativeQuery=true)
    List<History> findByDateOfStatus(@Param("Date") Date Date);

    @Query(value="SELECT * FROM history WHERE date_of_status  BETWEEN ? AND ?", nativeQuery =true)
    List<History> findByDateOfStatusBetweenDate1AndDate2(@Param("date1") Date dt1, @Param("date2") Date dt2);
}
