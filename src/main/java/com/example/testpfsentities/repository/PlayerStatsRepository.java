package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.PlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerStatsRepository extends JpaRepository<PlayerStats, String> {
    @Query("SELECT c FROM PlayerStats c WHERE c.player=:player AND c.match_id=:match_id")
    Optional<PlayerStats> findByPlayerAAndMatch_id(@Param("player") Player player, @Param("match_id") String match_id);
}
