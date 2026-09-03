package com.example.campuseventmanager.dto.organizerdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class OrganizerUpdateRequestDto {
    @Getter
    @Setter
    @NotBlank
    private String name;
    @Getter
    @Setter
    @NotBlank
    @Email
    private String email;
    @Getter
    @Setter
    @NotBlank
    private String department;
}
