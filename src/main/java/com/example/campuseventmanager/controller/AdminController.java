package com.example.campuseventmanager.controller;

import com.example.campuseventmanager.dto.admindto.AdminRegisterRequestDto;
import com.example.campuseventmanager.dto.admindto.AdminRegisterResponseDto;
import com.example.campuseventmanager.dto.admindto.AdminUpdateRequestDto;
import com.example.campuseventmanager.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/register")
    public ResponseEntity<AdminRegisterResponseDto> registerAdmin(@Valid @RequestBody AdminRegisterRequestDto adminRegisterRequestDto){
        return adminService.registerAdmin(adminRegisterRequestDto);
    }
    @PutMapping("/update")
    public ResponseEntity<AdminRegisterResponseDto>  updateAdmin(@Valid @RequestBody AdminUpdateRequestDto adminUpdateRequestDto){
        return adminService.updateAdmin(adminUpdateRequestDto);
    }
}
