package com.lucas.springbootessentials.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lucas.springbootessentials.exception.ResourceNotFoundException;
import com.lucas.springbootessentials.model.Student;
import com.lucas.springbootessentials.repository.StudentRepository;

@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/list")
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id){
        Optional<Student> student = studentRepository.findById(id);

        if(student.isPresent())
            return ResponseEntity.ok(student.get());
        else
            return ResponseEntity.badRequest().body(new ResourceNotFoundException("Não foi encontrado nenhum aluno com este ID!"));
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
     
    @DeleteMapping("/{id")
    public ResponseEntity<?> removeStudent(@PathVariable Long id){
        Optional<Student> student = studentRepository.findById(id);

        if(student.isPresent()) {
        	studentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.badRequest().body(new ResourceNotFoundException("Não foi encontrado nenhum aluno com este ID!"));
    }
    
}
