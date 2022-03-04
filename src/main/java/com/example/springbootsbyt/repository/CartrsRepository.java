package com.example.springbootsbyt.repository;

import com.example.springbootsbyt.model.Cartrs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartrsRepository extends JpaRepository<Cartrs, Long> {

}
