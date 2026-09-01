package com.example.campuseventmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class OrganizerRegisterRequestDto {
    @NotBlank
    @Getter
    @Setter
    private String name;
    @NotBlank
    @Getter
    @Setter
    private String email;
    @NotBlank
    @Getter
    @Setter
    private String password;
    @NotBlank
    @Getter
    @Setter
    private String department;
}
