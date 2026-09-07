package com.example.campuseventmanager.service;

import com.example.campuseventmanager.model.Admin;
import com.example.campuseventmanager.model.Organizer;
import com.example.campuseventmanager.model.Student;
import com.example.campuseventmanager.repo.AdminRepo;
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
    @Autowired
    private AdminRepo adminRepo;

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
        Admin admin=adminRepo.findByUsername(username);
        if(admin!=null){
            return User.builder()
                    .username(admin.getUsername())
                    .password(admin.getPassword())
                    .roles("ADMIN")
                    .build();
        }

        throw new UsernameNotFoundException("User not found: " + username);
    }
}