package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team,String> {
    Optional<Team> findByName(String name);
}
