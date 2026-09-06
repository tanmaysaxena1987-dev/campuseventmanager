package com.example.campuseventmanager.exception;

public class EventCapacityReached extends RuntimeException {
    public EventCapacityReached(String message) {
        super(message);
    }
}
