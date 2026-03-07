package com.ved.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ved.students.model.Student;
import com.ved.students.repository.StudentRepository;
import com.ved.students.dto.StudentRequestDTO;
import com.ved.students.dto.StudentResponseDTO;
import com.ved.students.exception.InvalidInputException;
import com.ved.students.exception.StudentNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Service
public class StudentsServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repo;

    // GET ALL STUDENTS
    @Override
    public List<StudentResponseDTO> getAllStudents() {
        return repo.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // GET STUDENT BY ID
    @Override
    public StudentResponseDTO getStudent(int id) {

        Student student = repo.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with id " + id)
                );

        return toResponseDTO(student);
    }

    // ADD STUDENT
    @Override
    public StudentResponseDTO addStudent(StudentRequestDTO studentDTO) {

        if (studentDTO.getName() == null || studentDTO.getName().isBlank()) {
            throw new InvalidInputException("Name cannot be empty");
        }

        if (studentDTO.getPercentage() < 0 || studentDTO.getPercentage() > 100) {
            throw new InvalidInputException("Percentage must be between 0 and 100");
        }

        Student student = toEntity(studentDTO);

        Student savedStudent = repo.save(student);

        return toResponseDTO(savedStudent);
    }

    // UPDATE STUDENT
    @Override
    public StudentResponseDTO updateStudent(int id, StudentRequestDTO studentDTO) {

        Student student = repo.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with id " + id)
                );

        student.setName(studentDTO.getName());
        student.setBranch(studentDTO.getBranch());
        student.setPercentage(studentDTO.getPercentage());

        Student updatedStudent = repo.save(student);

        return toResponseDTO(updatedStudent);
    }

    // DELETE STUDENT
    @Override
    public void deleteStudent(int id) {

        Student student = repo.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with id " + id)
                );

        repo.delete(student);
    }

    // PAGINATION
    @Override
    public List<StudentResponseDTO> getStudentsPaginated(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Student> studentPage = repo.findAll(pageable);

        return studentPage.getContent()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // ENTITY → RESPONSE DTO
    private StudentResponseDTO toResponseDTO(Student student) {

        return new StudentResponseDTO(
                student.getRollNo(),
                student.getName(),
                student.getBranch(),
                student.getPercentage()
        );
    }

    // REQUEST DTO → ENTITY
    private Student toEntity(StudentRequestDTO dto) {

        Student student = new Student();
        student.setName(dto.getName());
        student.setBranch(dto.getBranch());
        student.setPercentage(dto.getPercentage());

        return student;
    }
}