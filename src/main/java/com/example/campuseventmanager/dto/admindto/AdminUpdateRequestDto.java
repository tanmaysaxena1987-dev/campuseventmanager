package com.example.campuseventmanager.dto.admindto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class AdminUpdateRequestDto {
    @NotBlank
    @Getter
    @Setter
    private String name;
    @NotBlank
    @Getter
    @Setter
    private String email;
}
