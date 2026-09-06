package com.example.campuseventmanager.model;

import com.example.campuseventmanager.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.management.relation.Role;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {
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
    @Column(unique = true)
    private String username;
    @Getter
    @Setter
    @NotBlank
    private String password;
    @Getter
    @Setter
    @NotBlank
    @Email
    private String email;
    @Getter
    @Setter
    @NotNull
    private int semester;
    @Getter
    @Setter
    @NotBlank
    private String department;
    @Enumerated(EnumType.STRING)
    private Roles role= Roles.STUDENT;
    @CreationTimestamp
    @Getter
    @Setter
    private LocalDateTime createdDate;
    @UpdateTimestamp
    @Getter
    @Setter
    private LocalDateTime updatedDate;
    @Getter
    @Setter
    @ManyToMany
    @JoinTable(
            name = "student_event",
            joinColumns = @JoinColumn(name="eventId"),
            inverseJoinColumns = @JoinColumn(name = "student_Id")
    )
    private List<Event> events=new ArrayList<>();
}
