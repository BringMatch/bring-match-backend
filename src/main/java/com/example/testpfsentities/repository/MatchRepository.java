package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match,String> {
}
