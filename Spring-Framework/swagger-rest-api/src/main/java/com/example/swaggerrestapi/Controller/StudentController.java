package com.example.swaggerrestapi.Controller;

import com.example.swaggerrestapi.Model.Student;
import com.example.swaggerrestapi.Service.StudentService;
import com.github.javafaker.Faker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/student")
@Api(value = "StudentControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService){
        this.studentService = studentService;
    }

    @RequestMapping(path = "/createStudents", method = RequestMethod.GET)
    public List<Student> createStudents(){
        Faker faker = new Faker();
        for (int i = 0; i < 4; i++){
            Student tempStudent = new Student();
            tempStudent.setName(faker.name().fullName());
            tempStudent.setAge(faker.number().numberBetween(10, 50));
            tempStudent.setPhone(faker.phoneNumber().phoneNumber());

            studentService.saveStudent(tempStudent);
        }
        return studentService.getAllStudents();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    @ApiOperation("Gets student with specific id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Student.class)
    })
    public Student getStudent(@PathVariable(name = "id") String id){
        return studentService.getStudent(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Save student")
    public Student saveStudent(@RequestBody Student studentToSave){
        return studentService.saveStudent( studentToSave);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update student with specific id")
    public Student updateStudent(@RequestBody Student studentToUpdate,
                                 @PathVariable(name = "id") String id){
        return studentService.updateStudent( studentToUpdate, id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    @ApiOperation("Delete student with specific id")
    public void deleteStudent(@PathVariable(name = "id") String id){
        studentService.deleteStudent(id);
    }
}
