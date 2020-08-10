package com.example.swaggerrestapi.Service;

import com.example.swaggerrestapi.Model.Student;
import com.example.swaggerrestapi.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student getStudent(String id){
        return studentRepository.findById(id).orElse(null);
    }

    public Student saveStudent(Student studentToSave) {
        Student student;
        try {
            student = studentRepository.save(studentToSave);
            return student;
        }catch (Exception e){

        }
        return new Student();
    }

     public Student updateStudent(Student studentToUpdate, String id) {
        Student foundStudent = studentRepository.getOne(id);
        try{
            foundStudent.setId(studentToUpdate.getId());
            foundStudent.setName(studentToUpdate.getName());
            foundStudent.setPhone(studentToUpdate.getPhone());
            foundStudent.setAge(studentToUpdate.getAge());
            return studentRepository.save(foundStudent);
        }catch (Exception e){

        }
        return foundStudent;

    }

    public void deleteStudent(String id) {
        try{
            studentRepository.deleteById(id);
        }catch (Exception e){

        }
    }


}
