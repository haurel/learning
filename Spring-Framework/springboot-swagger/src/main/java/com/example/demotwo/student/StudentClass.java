package com.example.demotwo.student;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Student class")
public class StudentClass {
    @ApiModelProperty(value = "Student first name", example = "Vatsal")
    private String firstName;
    @ApiModelProperty(value = "Student last name", example = "Riotal")
    private String lastName;
    @ApiModelProperty(value = "Student phone number", example = "0612233122")
    private String phone;
    @ApiModelProperty(value = "Student's age", example = "23")
    private Integer age;
}
