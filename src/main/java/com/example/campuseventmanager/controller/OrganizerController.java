package com.example.campuseventmanager.controller;

import com.example.campuseventmanager.dto.eventdto.EventRegisterRequestDto;
import com.example.campuseventmanager.dto.eventdto.EventRegisterResponseDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerRegisterRequestDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerRegisterResponseDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerUpdateRequestDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerUpdateResponseDto;
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
    @PostMapping("/registerevent")
    public ResponseEntity<EventRegisterResponseDto> registerEvent(@Valid @RequestBody EventRegisterRequestDto eventRegisterRequestDto) {
        return organizerService.registerEvent(eventRegisterRequestDto);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<OrganizerUpdateResponseDto> updateOrganizer(@PathVariable Long id,@Valid @RequestBody OrganizerUpdateRequestDto organizerUpdateRequestDto) {
        return organizerService.updateOrganizer(id,organizerUpdateRequestDto);
    }
}
