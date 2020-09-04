package com.example.SpringBootHib1.entityImpl;

import com.example.SpringBootHib1.entity.Admin;
import com.example.SpringBootHib1.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminImpl {

    @Autowired
    private AdminRepository adminRepository;

    public boolean saveAdmin(Admin admin){
        try{
            adminRepository.save(admin);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkStudent(String email, String password){
        try{
            Admin admin = adminRepository.getOne(email);
            return admin.getPassword().equals(password);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean addAssignment(String email, String assignment){
      try{
          Admin admin = adminRepository.getOne(email);
          admin.setAssignment(assignment);
          adminRepository.save(admin);
          return true;
      }catch (Exception e){
          System.out.println(e);
          return false;
      }
    };

    public List<String> getAssignments(){
        List<String> assignment = adminRepository.getAssignments();
        return assignment;
    }


}
