package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player , String> {

}
