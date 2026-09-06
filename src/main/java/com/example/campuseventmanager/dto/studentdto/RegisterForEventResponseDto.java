package com.example.campuseventmanager.dto.studentdto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
public class RegisterForEventResponseDto {
    @Getter
    @Setter
    private String message="Event Registration Successful";
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private LocalDateTime date;
    @Getter
    @Setter
    private String Location;

    public RegisterForEventResponseDto(String name,LocalDateTime eventDate, @NotBlank String location) {
        this.name = name;
        this.date=eventDate;
        this.Location=location;
    }
}
