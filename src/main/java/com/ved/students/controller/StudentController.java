package com.ved.students.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ved.students.model.Student;
import com.ved.students.repository.StudentRepository;

@RestController
public class StudentController {
    @Autowired
    StudentRepository repo;
    @GetMapping("/student")
    public List<Student> getAllStudents() {
        List<Student> all = repo.findAll();
        return all;
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable int id) {
        Student student = repo.findById(id).get();
        return student;
    }
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add")
    public String addStudent(@RequestBody Student student ) {
        repo.save(student);
        return "Student added!";
    }

    @PutMapping("/student/update/{id}")
    public String updateStudent(@PathVariable int id) {
        Student student = repo.findById(id).get();
        student.setName("chutiya");
        student.setBranch("nothing");
        repo.save(student);
        return "changes made!";
    }

    @DeleteMapping("/student/{id}")
    public String deleteStudent(@PathVariable int id) {
        Student student = repo.findById(id).get();
        repo.delete(student);
        return "student details deleted";
    }
}
