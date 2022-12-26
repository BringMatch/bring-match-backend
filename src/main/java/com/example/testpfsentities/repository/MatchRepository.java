package com.example.testpfsentities.repository;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.entities.Match;
import com.sun.istack.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match,String> {
    List<MatchDto> findByGroundName(String groundName);

    @Query(value = "SELECT c FROM Match c" +
            " where c.date BETWEEN :date AND :currentDate "
    )
    List<Match> findByDate(@Nullable @Param("date")  Date date,Date currentDate);

    @Query(value = "SELECT c FROM Match c" +
            " where c.date > :currentDate "
    )
    List<Match> findAllBycurrentDate(Date currentDate);
    @Query(value = "SELECT c FROM Match c" +
            " where c.matchStatus ='PLAYED' "
    )
    List<Match> findAllByMatchStatus_Played();
    @Query(value = "SELECT c FROM Match c" +
            " where c.matchStatus ='PENDING' "
    )
    List<Match> findAllByMatchStatus_Pending();

//    @Query(value = "SELECT c FROM Match c" +
//            " where LOWER (c.town)=:town " +
//            "and LOWER (c.region)=:region "
//    )
//    List<Match> findMatchsByRegionAndTown(
//            @Param("town") String town,
//            @Param("region") String region
//    );

}
