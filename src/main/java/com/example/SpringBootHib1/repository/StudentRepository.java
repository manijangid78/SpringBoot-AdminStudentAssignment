package com.example.SpringBootHib1.repository;

import com.example.SpringBootHib1.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {

}
