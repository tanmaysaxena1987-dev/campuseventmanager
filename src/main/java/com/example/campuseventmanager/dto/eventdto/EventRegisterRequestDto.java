package com.example.campuseventmanager.dto.eventdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class EventRegisterRequestDto {
    @Getter
    @Setter
    @NotNull
    private Long organizerId;
    @Getter
    @Setter
    @NotBlank
    private String title;
    @Getter
    @Setter
    @NotBlank
    private String description;
    @Getter
    @Setter
    @NotBlank
    private String location;
    @Getter
    @Setter
    private LocalDateTime eventDate;
    @Getter
    @Setter
    @NotNull
    private int capacity;
}
