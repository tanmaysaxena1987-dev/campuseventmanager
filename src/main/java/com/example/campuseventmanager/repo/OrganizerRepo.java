package com.example.campuseventmanager.repo;

import com.example.campuseventmanager.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepo extends JpaRepository<Organizer, Long> {
}
