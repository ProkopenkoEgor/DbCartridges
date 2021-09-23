package com.example.springbootsbyt.repository;

import com.example.springbootsbyt.model.PrintCartr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrintCartRepository extends JpaRepository<PrintCartr,Integer> {
}
