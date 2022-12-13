package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {

    @Query("select c from Player c" +
            " WHERE LOWER(c.firstName)=:firstName" +
            " and LOWER(c.lastName)=:lastName" +
            " and LOWER(c.town)=:town" +
            " and LOWER(c.region)=:region"
    )
    List<Player> findByFirstNameAndTownAndRegionAndLastName(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("town") String town,
            @Param("region") String region
    );
}
