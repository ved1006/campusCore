package com.ved.students.service;

import java.util.List;

import com.ved.students.dto.StudentRequestDTO;
import com.ved.students.dto.StudentResponseDTO;

public interface StudentService {

    List<StudentResponseDTO> getAllStudents();

    StudentResponseDTO getStudent(int id);

    StudentResponseDTO addStudent(StudentRequestDTO studentDTO);

    StudentResponseDTO updateStudent(int id, StudentRequestDTO studentDTO);

    void deleteStudent(int id);

    List<StudentResponseDTO> getStudentsPaginated(int page,int size,String sortBy,String direction);
}