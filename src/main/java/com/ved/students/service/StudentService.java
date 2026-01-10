package com.ved.students.service;

import java.util.List;

import com.ved.students.model.Student;

public interface StudentService {
    
    List<Student> getAllStudents();

    Student getStudent(int id);

    void addStudent(Student student);

    void updateStudent(int id,Student student);

    void deleteStudent(int id);

    List<Student> getStudentsPaginated(int page,int size,String sortBy,String direction);
    //{
    //     Pageable pageable = PageRequest.of(page,size,sortBy,direction);
    //     Page<Student> page = repo.findAll(pageable);

    // }

}   
