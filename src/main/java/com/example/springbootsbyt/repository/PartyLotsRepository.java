package com.example.springbootsbyt.repository;

import com.example.springbootsbyt.model.Partylots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyLotsRepository extends JpaRepository<Partylots, Integer> {
}
