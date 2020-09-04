package com.example.SpringBootHib1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Admin")
public class Admin {

    @Id
    private String email;

    private String name;
    private String password;
    private String assignment;

}
