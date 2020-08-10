package com.example.swaggerrestapi.Controller;

import com.example.swaggerrestapi.Model.Student;
import com.example.swaggerrestapi.Service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/student")
@Api(value = "StudentControllerAPI")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService){
        this.studentService = studentService;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    @ApiOperation("Gets student with specific id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    public Student getStudent(@PathVariable(name = "id") String id){
        return studentService.getStudent(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Student saveStudent(@RequestBody Student studentToSave){
        return studentService.saveStudent( studentToSave);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public Student updateStudent(@RequestBody Student studentToUpdate,
                                 @PathVariable(name = "id") String id){
        return studentService.updateStudent( studentToUpdate, id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable(name = "id") String id){
        studentService.deleteStudent(id);
    }
}
