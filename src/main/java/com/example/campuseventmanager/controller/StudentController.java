package com.example.campuseventmanager.controller;

import com.example.campuseventmanager.dto.studentdto.StudentRegisterRequestDto;
import com.example.campuseventmanager.dto.studentdto.StudentRegisterResponseDto;
import com.example.campuseventmanager.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/register")
    public ResponseEntity<StudentRegisterResponseDto> registerStudent(@Valid @RequestBody StudentRegisterRequestDto studentRegisterRequestDto) {
        return studentService.registerStudent(studentRegisterRequestDto);
    }
    @GetMapping("/view")
    public ResponseEntity<StudentRegisterResponseDto> viewStudent() {
        return studentService.viewStudent();
    }
}
