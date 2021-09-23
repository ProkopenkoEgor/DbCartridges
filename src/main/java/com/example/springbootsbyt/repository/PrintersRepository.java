package com.example.springbootsbyt.repository;

import com.example.springbootsbyt.model.Printers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrintersRepository extends JpaRepository<Printers,Integer> {
}
