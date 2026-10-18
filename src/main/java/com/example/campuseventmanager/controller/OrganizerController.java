package com.example.campuseventmanager.controller;

import com.example.campuseventmanager.dto.eventdto.EventRegisterRequestDto;
import com.example.campuseventmanager.dto.eventdto.EventRegisterResponseDto;
import com.example.campuseventmanager.dto.eventdto.EventUpdateRequestDto;
import com.example.campuseventmanager.dto.eventdto.EventUpdateResponseDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerRegisterRequestDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerRegisterResponseDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerUpdateRequestDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerUpdateResponseDto;
import com.example.campuseventmanager.model.Organizer;
import com.example.campuseventmanager.service.OrganizerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Organizer> getOrganizer() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(organizerService.getOrganizer());
    }
    @PostMapping("/registerevent")
    public ResponseEntity<EventRegisterResponseDto> registerEvent(@Valid @RequestBody EventRegisterRequestDto eventRegisterRequestDto) {
        return organizerService.registerEvent(eventRegisterRequestDto);
    }
    @PutMapping("/update")
    public ResponseEntity<OrganizerUpdateResponseDto> updateOrganizer(@Valid @RequestBody OrganizerUpdateRequestDto organizerUpdateRequestDto) {
        return organizerService.updateOrganizer(organizerUpdateRequestDto);
    }
    @PutMapping("/updateevent/{id}")
    public ResponseEntity<EventUpdateResponseDto> updateEvent(@PathVariable Long id, @Valid @RequestBody EventUpdateRequestDto eventUpdateRequestDto) {
        return organizerService.updateEvent(id, eventUpdateRequestDto);
    }
    @GetMapping("/geteventregistration/{id}")
    public ResponseEntity<List<Long>>getRegistrationsforeEvent(@PathVariable Long id){
        return organizerService.getRegistrationsforEvent(id);
    }
    @GetMapping("/login")
    public ResponseEntity<String> loginOrganizer(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(organizerService.loginOrganizer());
    }
}
