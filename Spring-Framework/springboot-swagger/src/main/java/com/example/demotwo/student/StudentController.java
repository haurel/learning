package com.example.demotwo.student;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    private List<StudentClass> studentList = new ArrayList<>();

    @GetMapping("/student")
    @ApiOperation(value = "Return list with all students", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Error ...."),
            @ApiResponse(code = 501, message = "Error 2 ....")
    })
    public List<StudentClass> students(){
        return studentList;
    }

    @PostMapping("/student")
    @ApiOperation(value = "Add new student", response = StudentClass.class)
    public StudentClass addStudent(
          @ApiParam(value = "Write first name", required = true) @RequestParam String firstName,
          @ApiParam(value = "Write last name", required = true) @RequestParam String lastName,
          @ApiParam(value = "Write phone number", required = true) @RequestParam String phone,
          @ApiParam(value = "Write age", required = true) @RequestParam Integer age
    ){
        StudentClass newStudent = StudentClass.builder()
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .age(age)
                .build();

        studentList.add(newStudent);
        return newStudent;
    }



}
