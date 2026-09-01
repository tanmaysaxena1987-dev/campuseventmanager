package com.example.campuseventmanager.service;

import com.example.campuseventmanager.dto.OrganizerRegisterRequestDto;
import com.example.campuseventmanager.dto.OrganizerRegisterResponseDto;
import com.example.campuseventmanager.model.Organizer;
import com.example.campuseventmanager.repo.OrganizerRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrganizerService {
    @Autowired
    private OrganizerRepo organizerRepo;
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
