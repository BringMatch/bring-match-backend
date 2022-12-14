package com.example.testpfsentities.repository;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.entities.Ground;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroundRepository extends JpaRepository<Ground, String> {
    Optional<Ground> findByName(String name);

    List<Ground> findByOwner_Id(String owner_id);

    @Query(value = "SELECT c FROM Ground c " +
            "WHERE ( :town IS NULL or c.town like :town )" +
            "OR (:groundName IS NULL or c.name like :groundName)"
    )
    List<Ground> findAllGroundByTownAndRegion(
            @Param("town") String town,
            @Param("groundName") String name
    );


}
