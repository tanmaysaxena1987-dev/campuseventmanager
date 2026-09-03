package com.example.campuseventmanager.service;

import com.example.campuseventmanager.dto.eventdto.EventRegisterRequestDto;
import com.example.campuseventmanager.dto.eventdto.EventRegisterResponseDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerRegisterRequestDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerRegisterResponseDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerUpdateRequestDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerUpdateResponseDto;
import com.example.campuseventmanager.exception.OrganizerNotFound;
import com.example.campuseventmanager.model.Event;
import com.example.campuseventmanager.model.Organizer;
import com.example.campuseventmanager.repo.EventRepo;
import com.example.campuseventmanager.repo.OrganizerRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizerService {
    @Autowired
    private OrganizerRepo organizerRepo;
    @Autowired
    private EventRepo eventRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public ResponseEntity<OrganizerRegisterResponseDto> registerOrganizer(@Valid OrganizerRegisterRequestDto organizerRegisterRequestDto) {
        Organizer organizer=mapOrganizerRegisterRequestDtotoOrganizer(organizerRegisterRequestDto);
        organizerRepo.save(organizer);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapOrganizertoOrganizerRegisterResponseDto(organizer));
    }
    public ResponseEntity<OrganizerRegisterResponseDto> getOrganizerById(Long id) {
        Organizer organizer=organizerRepo.getById(id);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(mapOrganizertoOrganizerRegisterResponseDto(organizer));
    }
    public ResponseEntity<EventRegisterResponseDto> registerEvent(EventRegisterRequestDto eventRegisterRequestDto) {
        Event event=mapOrganizerEventRegisterRequestDtotoEvent(eventRegisterRequestDto);
        eventRepo.save(event);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapEventtoOrganizerEventRegisterResponseDto(event));
    }
    public ResponseEntity<OrganizerUpdateResponseDto> updateOrganizer(Long id,OrganizerUpdateRequestDto organizerUpdateRequestDto) {
        Organizer organizer=organizerRepo.findById(id).orElseThrow(()->new OrganizerNotFound("organizer with ID "+id+" not found"));
        Organizer updated_organizer=mapOrganizerUpdateRequestDtotoOrganizer(organizer,organizerUpdateRequestDto);
        organizerRepo.save(updated_organizer);
        OrganizerUpdateResponseDto organizerUpdateResponseDto=mapOrganizertoOrganizerUpdateResponseDto(updated_organizer);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(organizerUpdateResponseDto);
    }
    public OrganizerUpdateResponseDto mapOrganizertoOrganizerUpdateResponseDto(Organizer organizer) {
        OrganizerUpdateResponseDto  organizerUpdateResponseDto=new OrganizerUpdateResponseDto();
        organizerUpdateResponseDto.setId(organizer.getId());
        organizerUpdateResponseDto.setName(organizer.getName());
        organizerUpdateResponseDto.setEmail(organizer.getEmail());
        organizerUpdateResponseDto.setRole(organizer.getRole());
        organizerUpdateResponseDto.setDepartment(organizer.getDepartment());
        return organizerUpdateResponseDto;
    }
    public Organizer mapOrganizerUpdateRequestDtotoOrganizer(Organizer organizer,OrganizerUpdateRequestDto organizerUpdateRequestDto) {
        organizer.setEmail(organizerUpdateRequestDto.getEmail());
        organizer.setName(organizerUpdateRequestDto.getName());
        organizer.setDepartment(organizerUpdateRequestDto.getDepartment());
        return organizer;
    }
    public Event mapOrganizerEventRegisterRequestDtotoEvent(EventRegisterRequestDto eventRegisterRequestDto) {
        Event event = new Event();
        event.setOrganizer(organizerRepo.getById(eventRegisterRequestDto.getOrganizerId()));
        event.setEventDate(eventRegisterRequestDto.getEventDate());
        event.setCapacity(eventRegisterRequestDto.getCapacity());
        event.setTitle(eventRegisterRequestDto.getTitle());
        event.setDescription(eventRegisterRequestDto.getDescription());
        event.setLocation(eventRegisterRequestDto.getLocation());
        return event;
    }
    public EventRegisterResponseDto mapEventtoOrganizerEventRegisterResponseDto(Event event) {
        EventRegisterResponseDto organizerEventRegisterResponseDto = new EventRegisterResponseDto();
        organizerEventRegisterResponseDto.setOrganizerId(event.getOrganizer().getId());
        organizerEventRegisterResponseDto.setTitle(event.getTitle());
        organizerEventRegisterResponseDto.setDescription(event.getDescription());
        organizerEventRegisterResponseDto.setCapacity(event.getCapacity());
        organizerEventRegisterResponseDto.setEventDate(event.getEventDate());
        organizerEventRegisterResponseDto.setId(event.getId());
        return organizerEventRegisterResponseDto;
    }
    public Organizer mapOrganizerRegisterRequestDtotoOrganizer(OrganizerRegisterRequestDto organizerRegisterRequestDto) {
        Organizer organizer=new Organizer();
        organizer.setName(organizerRegisterRequestDto.getName());
        organizer.setEmail(organizerRegisterRequestDto.getEmail());
        organizer.setPassword(encoder.encode(organizerRegisterRequestDto.getPassword()));
        organizer.setDepartment(organizerRegisterRequestDto.getDepartment());
        return organizer;
    }
    public OrganizerRegisterResponseDto mapOrganizertoOrganizerRegisterResponseDto(Organizer organizer) {
        OrganizerRegisterResponseDto organizerRegisterResponseDto=new OrganizerRegisterResponseDto();
        organizerRegisterResponseDto.setName(organizer.getName());
        organizerRegisterResponseDto.setEmail(organizer.getEmail());
        organizerRegisterResponseDto.setDepartment(organizer.getDepartment());
        organizerRegisterResponseDto.setId(organizer.getId());
        return organizerRegisterResponseDto;
    }
}
