package com.example.springbootsbyt.repository;

//import com.example.springbootsbyt.entity.Cartridges;
import com.example.springbootsbyt.model.Cartridges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartridgeRepository extends JpaRepository<Cartridges, Integer> {



}
