package com.ved.students.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ved.students.dto.StudentRequestDTO;
import com.ved.students.dto.StudentResponseDTO;
import com.ved.students.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
        public List<StudentResponseDTO> getAllStudents() {
            return studentService.getAllStudents();
        }

        @GetMapping("/{id}")
    public StudentResponseDTO getStudent(@PathVariable int id) {
        return studentService.getStudent(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public StudentResponseDTO addStudent(@RequestBody StudentRequestDTO studentDTO) {
        return studentService.addStudent(studentDTO);
    }

    @PutMapping("/{id}")
    public StudentResponseDTO updateStudent(@PathVariable int id,
                                            @RequestBody StudentRequestDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "Student details deleted";
    }

    @GetMapping("/paginated")
    public List<StudentResponseDTO> getStudentsPaginated(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String direction) {

        return studentService.getStudentsPaginated(page, size, sortBy, direction);
    }
}