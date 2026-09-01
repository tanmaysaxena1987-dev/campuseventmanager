package com.example.campuseventmanager.repo;

import com.example.campuseventmanager.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event, Long> {
}
