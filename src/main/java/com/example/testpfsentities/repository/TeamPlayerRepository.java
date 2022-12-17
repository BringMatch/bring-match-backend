package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.TeamPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamPlayerRepository extends JpaRepository<TeamPlayer, String> {
    @Query(value = "SELECT * FROM TeamPlayer where match_owner=TRUE ", nativeQuery = true)
    TeamPlayer getTeamPlayer();

}
