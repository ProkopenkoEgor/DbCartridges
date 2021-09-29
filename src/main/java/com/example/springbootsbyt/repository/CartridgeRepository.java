package com.example.springbootsbyt.repository;

//import com.example.springbootsbyt.entity.Cartridges;
import com.example.springbootsbyt.model.Cartridges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartridgeRepository extends JpaRepository<Cartridges, Integer> {

}
