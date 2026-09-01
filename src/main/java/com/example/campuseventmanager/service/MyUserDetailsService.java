package com.example.campuseventmanager.service;

import com.example.campuseventmanager.model.Organizer;
import com.example.campuseventmanager.repo.OrganizerRepo;
import com.sun.security.auth.UserPrincipal;
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
    // add Student/Admin repos once those are built

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Organizer organizer=organizerRepo.findByName(username);
        if (organizer != null) {
            return User.builder()
                    .username(organizer.getName())
                    .password(organizer.getPassword())
                    .roles("ORGANIZER")
                    .build();
        }
        throw new UsernameNotFoundException("User not found: " + username);
    }
}