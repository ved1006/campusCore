package com.ved.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ved.students.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
