package com.lucas.springbootessentials.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.springbootessentials.exception.ResourceNotFoundException;
import com.lucas.springbootessentials.model.Student;
import com.lucas.springbootessentials.repository.StudentRepository;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(value = "/list")
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent())
            return ResponseEntity.ok(student.get());
        else
            throw new ResourceNotFoundException("Não foi encontrado nenhum aluno com este ID!");
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> saveStudent(@Valid @RequestBody Student student){
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.CREATED).ok("Estudante salvo com sucesso!");
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
