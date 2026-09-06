package com.example.campuseventmanager.controller;

import com.example.campuseventmanager.dto.eventdto.EventRegisterRequestDto;
import com.example.campuseventmanager.dto.eventdto.EventRegisterResponseDto;
import com.example.campuseventmanager.dto.eventdto.EventUpdateRequestDto;
import com.example.campuseventmanager.dto.eventdto.EventUpdateResponseDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerRegisterRequestDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerRegisterResponseDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerUpdateRequestDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerUpdateResponseDto;
import com.example.campuseventmanager.service.OrganizerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {
    @Autowired
    private OrganizerService organizerService;
    @PostMapping("/register")
    public ResponseEntity<OrganizerRegisterResponseDto> registerOrganizer(@Valid @RequestBody OrganizerRegisterRequestDto organizerRegisterRequestDto) {
        return organizerService.registerOrganizer(organizerRegisterRequestDto);
    }
    @GetMapping("/view")
    public ResponseEntity<OrganizerRegisterResponseDto> getOrganizerById() {
        return organizerService.getOrganizerById();
    }
    @PostMapping("/registerevent")
    public ResponseEntity<EventRegisterResponseDto> registerEvent(@Valid @RequestBody EventRegisterRequestDto eventRegisterRequestDto) {
        return organizerService.registerEvent(eventRegisterRequestDto);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<OrganizerUpdateResponseDto> updateOrganizer(@PathVariable Long id,@Valid @RequestBody OrganizerUpdateRequestDto organizerUpdateRequestDto) {
        return organizerService.updateOrganizer(id,organizerUpdateRequestDto);
    }
    @PutMapping("/updateevent/{id}")
    public ResponseEntity<EventUpdateResponseDto> updateEvent(@PathVariable Long id, @Valid @RequestBody EventUpdateRequestDto eventUpdateRequestDto) {
        return organizerService.updateEvent(id, eventUpdateRequestDto);
    }
    @GetMapping("/geteventregistration/{id}")
    public ResponseEntity<List<Long>>getRegistrationsforeEvent(@PathVariable Long id){
        return organizerService.getRegistrationsforEvent(id);
    }
}
