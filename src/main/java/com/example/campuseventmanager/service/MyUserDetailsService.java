package com.example.campuseventmanager.service;

import com.example.campuseventmanager.model.Organizer;
import com.example.campuseventmanager.model.Student;
import com.example.campuseventmanager.repo.OrganizerRepo;
import com.example.campuseventmanager.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private OrganizerRepo organizerRepo;
    @Autowired
    private StudentRepo studentRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Organizer organizer = organizerRepo.findByUsername(username);
        if (organizer != null) {
            return User.builder()
                    .username(organizer.getUsername())
                    .password(organizer.getPassword())
                    .roles("ORGANIZER")
                    .build();
        }

        Student student = studentRepo.findByUsername(username);
        if (student != null) {
            return User.builder()
                    .username(student.getUsername())
                    .password(student.getPassword())
                    .roles("STUDENT")
                    .build();
        }

        throw new UsernameNotFoundException("User not found: " + username);
    }
}