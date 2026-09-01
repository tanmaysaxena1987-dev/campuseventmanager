package com.example.campuseventmanager.controller;

import com.example.campuseventmanager.dto.OrganizerRegisterRequestDto;
import com.example.campuseventmanager.dto.OrganizerRegisterResponseDto;
import com.example.campuseventmanager.service.OrganizerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {
    @Autowired
    private OrganizerService organizerService;
    @PostMapping("/register")
    public ResponseEntity<OrganizerRegisterResponseDto> registerOrganizer(@Valid @RequestBody OrganizerRegisterRequestDto organizerRegisterRequestDto) {
        return organizerService.registerOrganizer(organizerRegisterRequestDto);
    }
}
