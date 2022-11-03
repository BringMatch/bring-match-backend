package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.Admin;
import com.example.testpfsentities.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin , Long> {
//    @Query(name = "")
//    Owner createOwner();

}
