package com.ved.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ved.students.model.Student;
import com.ved.students.repository.StudentRepository;

@Service
public class StudentsServiceImpl implements StudentService {
    
    @Autowired
    private StudentRepository repo;

    @Override
    public List<Student> getAllStudents() {
        List<Student> all = repo.findAll();
        return all;
    }

    @Override
    public Student getStudent(int id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public void addStudent(Student student) {
        repo.save(student);
    }

    @Override
    public void updateStudent(int id, Student updatedStudent) {
        Student student = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(updatedStudent.getName());
        student.setBranch(updatedStudent.getBranch());
        student.setPercentage(updatedStudent.getPercentage());

        repo.save(student);
    }

    @Override
    public void deleteStudent(int id) {
        Student student = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        repo.delete(student);
    }

}
