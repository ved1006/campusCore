package com.ved.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ved.students.model.Student;
import com.ved.students.repository.StudentRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.ved.students.exception.InvalidInputException;
import com.ved.students.exception.StudentNotFoundException;

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
            .orElseThrow(() -> new StudentNotFoundException("Error: Student not found of this id " + id));
    }

    @Override
    public void addStudent(Student student) {
        if(student.getName() == null || student.getName().isBlank()) {
            throw new InvalidInputException("Name cannot be empty");   
        }

        if(student.getPercentage() < 0 || student.getPercentage() > 100) {
            throw new InvalidInputException("the percentage is invalid");
        }
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

    @Override
    public List<Student> getStudentsPaginated(int page,int size,String sortBy,String direction){

        Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();


        Pageable pageable = PageRequest.of(page, size,sort);
        Page<Student> studentPage = repo.findAll(pageable);

        return studentPage.getContent();
    }

}
