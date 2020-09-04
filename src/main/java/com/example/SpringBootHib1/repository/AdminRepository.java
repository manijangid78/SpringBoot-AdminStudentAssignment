package com.example.SpringBootHib1.repository;

import com.example.SpringBootHib1.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, String> {

        @Query(value = "SELECT assignment from admin", nativeQuery = true)
        public List<String> getAssignments();

}
