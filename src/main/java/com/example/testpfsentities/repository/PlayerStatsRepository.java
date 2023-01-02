package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.PlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerStatsRepository extends JpaRepository<PlayerStats, String> {
    Optional<PlayerStats> findByPlayer(Player player);
}
