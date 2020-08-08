package com.example.demotwo.student;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentClass {
    private String firstName;
    private String lastName;
    private String phone;
    private Integer age;
}
