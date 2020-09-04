package com.example.SpringBootHib1.entityImpl;

import com.example.SpringBootHib1.entity.Student;
import com.example.SpringBootHib1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentImpl {

    @Autowired
    private StudentRepository studentRepository;

    public boolean saveStudent(Student student){
        try{
            studentRepository.save(student);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean checkStudent(String email, String password){
        try{
            Student student = studentRepository.getOne(email);
            return student.getPassword().equals(password);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

}
