package com.example.campuseventmanager.model;

import com.example.campuseventmanager.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="organizer")
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    @NotBlank
    private String name;
    @Getter
    @Setter
    @NotBlank
    @Email
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String department;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Roles role= Roles.ORGANIZER;
    @Getter
    @Setter
    @CreationTimestamp
    private LocalDateTime createdDate;
    @Getter
    @Setter
    @UpdateTimestamp
    private LocalDateTime modifiedDate;
    @OneToMany()
    @Getter
    @Setter
    private List<Event> events=new ArrayList<>();
}
