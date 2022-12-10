package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match,String> {
    List<Match> findByDate(Date date);
}
