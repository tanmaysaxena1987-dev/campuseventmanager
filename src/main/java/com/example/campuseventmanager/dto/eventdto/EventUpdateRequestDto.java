package com.example.campuseventmanager.dto.eventdto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class EventUpdateRequestDto {
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
    @NotNull
    private LocalDateTime eventDate;
    @Getter
    @Setter
    @NotNull
    @Min(1)
    private int capacity;
}
