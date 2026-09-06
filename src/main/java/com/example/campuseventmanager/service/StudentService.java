package com.example.campuseventmanager.service;

import com.example.campuseventmanager.dto.studentdto.StudentRegisterRequestDto;
import com.example.campuseventmanager.dto.studentdto.StudentRegisterResponseDto;
import com.example.campuseventmanager.model.Student;
import com.example.campuseventmanager.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
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
