package com.example.campuseventmanager.dto.studentdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class StudentUpdateRequestDto {

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
    @NotNull
    private int semester;
    @Getter
    @Setter
    @NotNull
    private String department;
}
