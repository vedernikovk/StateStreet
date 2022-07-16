package com.statestreet.students.controller;

import com.statestreet.students.model.Student;
import com.statestreet.students.service.StudentNotFoundException;
import com.statestreet.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(consumes = { "application/json" })
    public ResponseEntity<Void> addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) throws StudentNotFoundException {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(params = { "courseName" }, produces = { "application/json" })
    public ResponseEntity<List<String>> findEnrolledStudents(@RequestParam("courseName") String courseName) {
        List<String> students = studentService.findEnrolledStudents(courseName);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping(params = { "ignoredCourseName" }, produces = { "application/json" })
    public ResponseEntity<List<String>> findUnEnrolledStudents(@RequestParam("ignoredCourseName") String courseName) {
        List<String> students = studentService.findNotEnrolledStudents(courseName);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String StudentNotFound(StudentNotFoundException e) {
        return "Student Not Found";
    }
}
