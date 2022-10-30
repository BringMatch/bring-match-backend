package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player , Long> {

}
