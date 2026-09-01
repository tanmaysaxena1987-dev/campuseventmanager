package com.example.campuseventmanager.controller;

import com.example.campuseventmanager.dto.OrganizerRegisterRequestDto;
import com.example.campuseventmanager.dto.OrganizerRegisterResponseDto;
import com.example.campuseventmanager.service.OrganizerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {
    @Autowired
    private OrganizerService organizerService;
    @PostMapping("/register")
    public ResponseEntity<OrganizerRegisterResponseDto> registerOrganizer(@Valid @RequestBody OrganizerRegisterRequestDto organizerRegisterRequestDto) {
        return organizerService.registerOrganizer(organizerRegisterRequestDto);
    }
    @GetMapping("/view/{id}")
    public ResponseEntity<OrganizerRegisterResponseDto> getOrganizerById(@PathVariable Long id) {
        return organizerService.getOrganizerById(id);
    }
}
