package com.example.campuseventmanager.dto.admindto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class AdminRegisterRequestDto {
    @Getter
    @Setter
    @NotBlank
    private String username;
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

}
