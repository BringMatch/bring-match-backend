package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic , Long> {
}
