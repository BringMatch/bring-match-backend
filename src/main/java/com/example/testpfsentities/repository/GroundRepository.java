package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.Ground;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroundRepository extends JpaRepository<Ground, String> {
    Optional<Ground> findByName(String name);
    List<Ground> findByOwner_Id(String owner_id);
}
