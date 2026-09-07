package com.example.campuseventmanager.dto.admindto;

import com.example.campuseventmanager.enums.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

public class AdminRegisterResponseDto {
    @Getter
    @Setter
    private String username;
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
    @Enumerated(EnumType.STRING)
    private Roles role=Roles.ADMIN;
}
