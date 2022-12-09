package com.example.testpfsentities.repository;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match,String> {
    List<MatchDto> findByGroundName(String groundName);


}
