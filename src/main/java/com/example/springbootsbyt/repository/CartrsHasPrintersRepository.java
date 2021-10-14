package com.example.springbootsbyt.repository;

import com.example.springbootsbyt.model.CartrsHasPrinters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartrsHasPrintersRepository extends JpaRepository<CartrsHasPrinters,Integer> {
}
