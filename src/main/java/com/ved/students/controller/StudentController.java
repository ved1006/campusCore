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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ved.students.model.Student;
import com.ved.students.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
    
     @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
        return studentService.getStudent(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return "Student added!";
    }

    @PutMapping("/{id}")
    public String updateStudent(@PathVariable int id,@RequestBody Student student) {
        studentService.updateStudent(id, student);
        return "Changes made!";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "Student details deleted";
    }

    @GetMapping("/paginated")
    public List<Student> getStudentsPaginated(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam String     direction) {
        return studentService.getStudentsPaginated(page,size,sortBy,direction);
    }
}