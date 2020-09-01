package com.lucas.springbootessentials.controller;

import com.lucas.springbootessentials.model.Student;
import com.lucas.springbootessentials.repository.StudentRepository;
import com.lucas.springbootessentials.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/list")
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        Optional<Student> student = studentRepository.findById(id);

        if(student.isPresent())
            return ResponseEntity.ok(student.get());
        else
            return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<?> saveStudent(@RequestBody Student student){
        studentRepository.save(student);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody Student studentP){
        Optional<Student> student = studentRepository.findById(studentP.getId());

        if(student.isPresent())
            student = Optional.ofNullable(studentP);
        else
            return ResponseEntity.badRequest().build();

        studentRepository.save(student.get());

        return ResponseEntity.ok().build();
    }
}
