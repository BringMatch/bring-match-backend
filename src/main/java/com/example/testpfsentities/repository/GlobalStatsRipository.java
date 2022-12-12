package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.GlobalStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalStatsRipository extends JpaRepository<GlobalStats,Long> {
}
