package com.example.campuseventmanager.dto.studentdto;

import lombok.Getter;
import lombok.Setter;

public class StudentRegisterResponseDto {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private int semester;
    @Getter
    @Setter
    private String department;
}
