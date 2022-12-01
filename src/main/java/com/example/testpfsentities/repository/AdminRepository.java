package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    @Query(value = "SELECT * FROM Admin LIMIT 1", nativeQuery = true)
    List<Admin> findFirstAdmin();


}
