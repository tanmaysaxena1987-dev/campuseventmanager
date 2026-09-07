package com.example.campuseventmanager.service;

import com.example.campuseventmanager.dto.eventdto.EventRegisterRequestDto;
import com.example.campuseventmanager.dto.eventdto.EventRegisterResponseDto;
import com.example.campuseventmanager.dto.eventdto.EventUpdateRequestDto;
import com.example.campuseventmanager.dto.eventdto.EventUpdateResponseDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerRegisterRequestDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerRegisterResponseDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerUpdateRequestDto;
import com.example.campuseventmanager.dto.organizerdto.OrganizerUpdateResponseDto;
import com.example.campuseventmanager.exception.EventNotFound;
import com.example.campuseventmanager.exception.OrganizerNotFound;
import com.example.campuseventmanager.model.Event;
import com.example.campuseventmanager.model.Organizer;
import com.example.campuseventmanager.model.Student;
import com.example.campuseventmanager.repo.EventRepo;
import com.example.campuseventmanager.repo.OrganizerRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<OrganizerRegisterResponseDto> getOrganizerById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Organizer CurrentOrganizer=organizerRepo.findByUsername(username);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(mapOrganizertoOrganizerRegisterResponseDto(CurrentOrganizer));
    }
    public ResponseEntity<EventRegisterResponseDto> registerEvent(EventRegisterRequestDto eventRegisterRequestDto) {
        Event event=mapOrganizerEventRegisterRequestDtotoEvent(eventRegisterRequestDto);
        eventRepo.save(event);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapEventtoOrganizerEventRegisterResponseDto(event));
    }
    public ResponseEntity<OrganizerUpdateResponseDto> updateOrganizer(OrganizerUpdateRequestDto organizerUpdateRequestDto) {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Organizer organizer=organizerRepo.findByUsername(username);
        Organizer updated_organizer=mapOrganizerUpdateRequestDtotoOrganizer(organizer,organizerUpdateRequestDto);
        organizerRepo.save(updated_organizer);
        OrganizerUpdateResponseDto organizerUpdateResponseDto=mapOrganizertoOrganizerUpdateResponseDto(updated_organizer);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(organizerUpdateResponseDto);
    }
    public ResponseEntity<EventUpdateResponseDto> updateEvent(Long id, EventUpdateRequestDto eventUpdateRequestDto) {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        Organizer organizer=organizerRepo.findByUsername(authentication.getName());
        Event event=eventRepo.findById(id).orElseThrow(()->new EventNotFound("event with ID "+id+" not found"));
        if(!event.getOrganizer().getId().equals(organizer.getId()))
            throw new AccessDeniedException("Access denied");
        Event updated_event=mapEventtoEventUpdateRequestDto(event,eventUpdateRequestDto);
        eventRepo.save(updated_event);
        EventUpdateResponseDto eventUpdateResponseDto=mapEventtoEventUpdateResponseDto(updated_event);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventUpdateResponseDto);

    }
    public ResponseEntity<List<Long>>getRegistrationsforEvent(Long eventId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Organizer organizer=organizerRepo.findByUsername(authentication.getName());
        Event event=eventRepo.findById(eventId).orElseThrow(()->new EventNotFound("event with ID "+eventId+" not found"));
        if(organizer.getId()==event.getOrganizer().getId()){
            List<Long> student_id=event.getStudents().stream().map(Student::getId).collect(Collectors.toList());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(student_id);
        }
        else throw new AccessDeniedException("access denied");
    }
    public Event mapEventtoEventUpdateRequestDto(Event event, EventUpdateRequestDto eventUpdateRequestDto) {
        event.setOrganizer(organizerRepo.getById(eventUpdateRequestDto.getOrganizerId()));
        event.setEventDate(eventUpdateRequestDto.getEventDate());
        event.setLocation(eventUpdateRequestDto.getLocation());
        event.setDescription(eventUpdateRequestDto.getDescription());
        event.setCapacity(eventUpdateRequestDto.getCapacity());
        return event;
    }
    private EventUpdateResponseDto mapEventtoEventUpdateResponseDto(Event event) {
        EventUpdateResponseDto eventUpdateResponseDto=new EventUpdateResponseDto();
        eventUpdateResponseDto.setOrganizerId(event.getOrganizer().getId());
        eventUpdateResponseDto.setTitle(event.getTitle());
        eventUpdateResponseDto.setId(event.getId());
        eventUpdateResponseDto.setDescription(event.getDescription());
        eventUpdateResponseDto.setCapacity(event.getCapacity());
        eventUpdateResponseDto.setEventDate(event.getEventDate());
        eventUpdateResponseDto.setLocation(event.getLocation());
        return eventUpdateResponseDto;

    }
    public OrganizerUpdateResponseDto mapOrganizertoOrganizerUpdateResponseDto(Organizer organizer) {
        OrganizerUpdateResponseDto  organizerUpdateResponseDto=new OrganizerUpdateResponseDto();
        organizerUpdateResponseDto.setId(organizer.getId());
        organizerUpdateResponseDto.setUsername(organizer.getUsername());
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
        organizer.setUsername("org_"+organizerRegisterRequestDto.getUsername());
        organizer.setName(organizerRegisterRequestDto.getName());
        organizer.setEmail(organizerRegisterRequestDto.getEmail());
        organizer.setPassword(encoder.encode(organizerRegisterRequestDto.getPassword()));
        organizer.setDepartment(organizerRegisterRequestDto.getDepartment());
        return organizer;
    }
    public OrganizerRegisterResponseDto mapOrganizertoOrganizerRegisterResponseDto(Organizer organizer) {
        OrganizerRegisterResponseDto organizerRegisterResponseDto=new OrganizerRegisterResponseDto();
        organizerRegisterResponseDto.setUsername(organizer.getUsername());
        organizerRegisterResponseDto.setName(organizer.getName());
        organizerRegisterResponseDto.setEmail(organizer.getEmail());
        organizerRegisterResponseDto.setDepartment(organizer.getDepartment());
        organizerRegisterResponseDto.setId(organizer.getId());
        return organizerRegisterResponseDto;
    }
}
