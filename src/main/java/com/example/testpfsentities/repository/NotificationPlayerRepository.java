package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.NotificationPlayer;
import com.example.testpfsentities.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationPlayerRepository extends JpaRepository<NotificationPlayer,String> {
    @Query("SELECT c FROM NotificationPlayer c WHERE c.player=:player ORDER BY c.createdAt DESC")
    List<NotificationPlayer> findNotificationPlayerByOwner_matchAnd(@Param("player") Player player);


    List<NotificationPlayer> findByPlayer(Player player);
}
