package com.example.campuseventmanager.controller;

import com.example.campuseventmanager.dto.studentdto.RegisterForEventResponseDto;
import com.example.campuseventmanager.dto.studentdto.StudentRegisterRequestDto;
import com.example.campuseventmanager.dto.studentdto.StudentRegisterResponseDto;
import com.example.campuseventmanager.dto.studentdto.StudentUpdateRequestDto;
import com.example.campuseventmanager.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/student")
@Tag(name = "Student Api",description = "Student related api")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/register")
    public ResponseEntity<StudentRegisterResponseDto> registerStudent(@Valid @RequestBody StudentRegisterRequestDto studentRegisterRequestDto) {
        return studentService.registerStudent(studentRegisterRequestDto);
    }
    @GetMapping("/view")
    @Operation(summary = "view a student")
    public ResponseEntity<StudentRegisterResponseDto> viewStudent() {
        return studentService.viewStudent();
    }
    @PutMapping("/update")
    public ResponseEntity<StudentRegisterResponseDto> updateStudent(@Valid @RequestBody StudentUpdateRequestDto studentUpdateRequestDto) {
        return studentService.updateStudent(studentUpdateRequestDto);
    }
    @PutMapping("/registerforevent/{eventId}")
    public ResponseEntity<RegisterForEventResponseDto> registerForEvent(@PathVariable Long eventId) {
        return studentService.registerForEvent(eventId);
    }
    @GetMapping("/getevents")
    public ResponseEntity<List<Long>> getEventRegistration(){
        return studentService.getEventRegistration();
    }
}
