package com.example.swaggerrestapi.Service;

import com.example.swaggerrestapi.Model.Student;
import com.example.swaggerrestapi.Repository.StudentRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {

    private Logger LOG = LoggerFactory.getLogger(StudentService.class);

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student getStudent(String id){
        LOG.info("Getting student with given id: " + id);
        return studentRepository.findById(id).orElse(null);
    }

    public Student saveStudent(Student studentToSave) {
        Student student;
        try {
            LOG.info("Saving student ...");
            student = studentRepository.save(studentToSave);
            return student;
        }catch (Exception e){
            LOG.error("Error during student saving: " + e.getMessage());
        }
        return new Student();
    }

     public Student updateStudent(Student studentToUpdate, String id) {
        Student foundStudent = studentRepository.getOne(id);
        try{
            foundStudent.setName(studentToUpdate.getName());
            foundStudent.setPhone(studentToUpdate.getPhone());
            foundStudent.setAge(studentToUpdate.getAge());
            return studentRepository.save(foundStudent);
        }catch (Exception e){
            LOG.error("Error during update of student: " + e.getMessage());
        }
        return foundStudent;
    }

    public void deleteStudent(String id) {
        try{
            studentRepository.deleteById(id);
        }catch (Exception e){
            LOG.error("Error during deleting of student: " + e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
