package com.example.campuseventmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
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
    private int capacity;
    @ManyToOne
    @JoinColumn(name ="organizer_id")
    @Getter
    @Setter
    private Organizer organizer;
    @ManyToMany(mappedBy = "events")
    @Getter
    @Setter
    private List<Student> students;
}
