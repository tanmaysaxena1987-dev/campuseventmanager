package com.example.campuseventmanager.dto.studentdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

public class StudentRegisterRequestDto {
    @NotBlank
    @Getter
    @Setter
    private String name;
    @NotBlank
    @Getter
    @Setter
    @Email
    private String email;
    @Getter
    @Setter
    @NotBlank
    private String password;
    @Getter
    @Setter
    @NotBlank
    private String username;
    @Getter
    @Setter
    @NotNull
    private int semester;
    @Getter
    @Setter
    @NotNull
    private String department;
}
