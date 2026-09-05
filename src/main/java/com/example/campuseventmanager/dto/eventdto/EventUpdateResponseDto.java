package com.example.campuseventmanager.dto.eventdto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class EventUpdateResponseDto {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Long organizerId;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String location;
    @Getter
    @Setter
    private LocalDateTime eventDate;
    @Getter
    @Setter
    private int capacity;;
}
