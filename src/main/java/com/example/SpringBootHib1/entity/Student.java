package com.example.SpringBootHib1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="Users")
public class Student {

    @Id
    private String email;

    private String name;
    private String password;

}
