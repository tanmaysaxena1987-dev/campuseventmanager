package com.example.campuseventmanager.dto.organizerdto;

import com.example.campuseventmanager.enums.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

public class OrganizerRegisterResponseDto {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String department;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Roles role=Roles.ORGANIZER;
}
