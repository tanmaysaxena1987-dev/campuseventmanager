package com.example.campuseventmanager.service;

import com.example.campuseventmanager.dto.admindto.AdminRegisterRequestDto;
import com.example.campuseventmanager.dto.admindto.AdminRegisterResponseDto;
import com.example.campuseventmanager.dto.admindto.AdminUpdateRequestDto;
import com.example.campuseventmanager.exception.AdminNotFound;
import com.example.campuseventmanager.model.Admin;
import com.example.campuseventmanager.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
    public ResponseEntity<AdminRegisterResponseDto> registerAdmin(AdminRegisterRequestDto adminRegisterRequestDto) {
        Admin admin=mapAdmintoAdminRegisterRequestDto(adminRegisterRequestDto);
        adminRepo.save(admin);
        AdminRegisterResponseDto adminRegisterResponseDto=mapAdmintoAdminRegisterResponseDto(admin);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(adminRegisterResponseDto);
    }
    public ResponseEntity<AdminRegisterResponseDto> updateAdmin(AdminUpdateRequestDto adminUpdateRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin=adminRepo.findByUsername(authentication.getName());
        if(admin==null)
            throw new AdminNotFound("admin with username "+authentication.getName()+" not found");
        Admin updated_admin=mapAdmintoAdminUpdateRequestDto(admin,adminUpdateRequestDto);
        adminRepo.save(updated_admin);
        AdminRegisterResponseDto adminUpdateResponseDto=mapAdmintoAdminRegisterResponseDto(updated_admin);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adminUpdateResponseDto);
    }
    private Admin mapAdmintoAdminUpdateRequestDto(Admin admin, AdminUpdateRequestDto adminUpdateRequestDto) {
        admin.setName(adminUpdateRequestDto.getName());
        admin.setEmail(adminUpdateRequestDto.getEmail());
        return admin;
    }
    private AdminRegisterResponseDto mapAdmintoAdminRegisterResponseDto(Admin admin) {
        AdminRegisterResponseDto adminRegisterResponseDto=new AdminRegisterResponseDto();
        adminRegisterResponseDto.setEmail(admin.getEmail());
        adminRegisterResponseDto.setName(admin.getName());
        adminRegisterResponseDto.setId(admin.getId());
        adminRegisterResponseDto.setUsername(admin.getUsername());
        adminRegisterResponseDto.setRole(admin.getRole());
        return adminRegisterResponseDto;
    }
    private Admin mapAdmintoAdminRegisterRequestDto(AdminRegisterRequestDto adminRegisterRequestDto) {
        Admin admin=new Admin();
        admin.setEmail(adminRegisterRequestDto.getEmail());
        admin.setName(adminRegisterRequestDto.getName());
        admin.setPassword(encoder.encode(adminRegisterRequestDto.getPassword()));
        admin.setUsername("adm_"+adminRegisterRequestDto.getUsername());
        return admin;
    }
}
