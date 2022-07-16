package com.statestreet.students.service;

import com.statestreet.students.model.Student;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);

    void deleteStudent(long id) throws StudentNotFoundException;

    List<String> findEnrolledStudents(String courseName);

    List<String> findNotEnrolledStudents(String courseName);
}
