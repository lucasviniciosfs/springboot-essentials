package com.lucas.springbootessentials.repository;

import com.lucas.springbootessentials.model.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	List<Student> findByName(String name);
}
