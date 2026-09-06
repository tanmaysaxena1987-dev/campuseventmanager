package com.example.campuseventmanager.service;

import com.example.campuseventmanager.dto.studentdto.*;
import com.example.campuseventmanager.exception.EventCapacityReached;
import com.example.campuseventmanager.exception.EventNotFound;
import com.example.campuseventmanager.model.Event;
import com.example.campuseventmanager.model.Student;
import com.example.campuseventmanager.repo.EventRepo;
import com.example.campuseventmanager.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private EventRepo  eventRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public ResponseEntity<StudentRegisterResponseDto> registerStudent(StudentRegisterRequestDto studentRegisterRequestDto) {
        Student student = mapStudenttoStudentRegisterRequestDto(studentRegisterRequestDto);
        studentRepo.save(student);
        StudentRegisterResponseDto studentRegisterResponseDto = mapStudentRegisterResponseDtotoStudent(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentRegisterResponseDto);
    }
    public ResponseEntity<StudentRegisterResponseDto> viewStudent(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Student student = studentRepo.findByUsername(authentication.getName());;
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(mapStudentRegisterResponseDtotoStudent(student));
    }
    public ResponseEntity<StudentRegisterResponseDto> updateStudent(StudentUpdateRequestDto studentUpdateRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Student student= studentRepo.findByUsername(authentication.getName());
        Student updated_student=mapStudenttoStudentUpdateRequestDto(student,studentUpdateRequestDto);
        studentRepo.save(updated_student);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapStudentRegisterResponseDtotoStudent(updated_student));
    }
    public ResponseEntity<RegisterForEventResponseDto> registerForEvent(Long eventId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Student student = studentRepo.findByUsername(authentication.getName());
        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new EventNotFound("Event with id " + eventId + " not found"));

        if (student.getEvents().contains(event)) {
            throw new IllegalArgumentException("Student with id " + student.getId() + " is already registered");
        }

        if (event.getCapacity() <= 0) {
            throw new EventCapacityReached("Capacity reached");
        }

        student.getEvents().add(event);
        event.setCapacity(event.getCapacity() - 1);
        studentRepo.save(student);

        RegisterForEventResponseDto registerForEventResponseDto =
                new RegisterForEventResponseDto(event.getTitle(), event.getEventDate(), event.getLocation());

        return ResponseEntity.status(HttpStatus.OK).body(registerForEventResponseDto);
    }
    public ResponseEntity<List<Long>>  getEventRegistration(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Student student = studentRepo.findByUsername(authentication.getName());
        List event_id=student.getEvents().stream().map(Event::getId).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(event_id);
    }
    private Student mapStudenttoStudentUpdateRequestDto(Student student,StudentUpdateRequestDto studentUpdateRequestDto) {
      student.setName(studentUpdateRequestDto.getName());
      student.setEmail(studentUpdateRequestDto.getEmail());
      student.setDepartment(studentUpdateRequestDto.getDepartment());
      student.setSemester(studentUpdateRequestDto.getSemester());
      return student;
    }
    private StudentRegisterResponseDto mapStudentRegisterResponseDtotoStudent(Student student) {
        StudentRegisterResponseDto studentRegisterResponseDto = new StudentRegisterResponseDto();
        studentRegisterResponseDto.setDepartment(student.getDepartment());
        studentRegisterResponseDto.setId(student.getId());
        studentRegisterResponseDto.setName(student.getName());
        studentRegisterResponseDto.setEmail(student.getEmail());
        studentRegisterResponseDto.setUsername(student.getUsername());
        studentRegisterResponseDto.setSemester(student.getSemester());
        return studentRegisterResponseDto;
    }
    private Student  mapStudenttoStudentRegisterRequestDto(StudentRegisterRequestDto studentRegisterRequestDto) {
        Student student = new Student();
        student.setDepartment(studentRegisterRequestDto.getDepartment());
        student.setUsername("stu_"+studentRegisterRequestDto.getUsername());
        student.setEmail(studentRegisterRequestDto.getEmail());
        student.setPassword(encoder.encode(studentRegisterRequestDto.getPassword()));
        student.setSemester(studentRegisterRequestDto.getSemester());
        student.setName(studentRegisterRequestDto.getName());
        return student;
    }
}
