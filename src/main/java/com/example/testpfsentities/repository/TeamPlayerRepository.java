package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.TeamPlayer;
import com.example.testpfsentities.entities.composite.TeamPlayerKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamPlayerRepository extends JpaRepository<TeamPlayer, TeamPlayerKey> {
}
