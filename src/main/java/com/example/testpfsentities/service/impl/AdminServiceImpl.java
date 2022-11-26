package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.entities.Admin;
import com.example.testpfsentities.repository.AdminRepository;
import com.example.testpfsentities.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;


    @Override
    public void initAdmin() {
        Admin admin = new Admin();
        admin.setEmail("yessinejawa@gmail.com");
        admin.setUpdatedAt(Instant.now());
        admin.setUserPassword("yessine");
        admin.setFirstName("ajaoua");
        admin.setLastName("ajaqsdfoua");
        admin.setPhoneNumber("45454");
        admin.setRoleName("admin");
        admin.setCreatedAt(Instant.now());
        adminRepository.save(admin);
    }
}
