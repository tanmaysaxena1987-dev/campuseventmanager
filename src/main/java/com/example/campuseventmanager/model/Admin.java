package com.example.campuseventmanager.model;

import com.example.campuseventmanager.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="admin")
public class Admin {
    @Getter
    @Setter
    @NotBlank
    @Column(unique = true)
    private String username;
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    @NotBlank
    private String name;
    @Getter
    @Setter
    @NotBlank
    private String password;
    @Getter
    @Setter
    @NotBlank
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private Roles role= Roles.ADMIN;
    @Getter
    @Setter
    @CreationTimestamp
    private LocalDateTime created;
    @Getter
    @Setter
    @UpdateTimestamp
    private LocalDateTime updated;
}
