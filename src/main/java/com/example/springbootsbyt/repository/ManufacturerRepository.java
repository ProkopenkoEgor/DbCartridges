package com.example.springbootsbyt.repository;

import com.example.springbootsbyt.model.Manufacturers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturers,Integer> {
}
