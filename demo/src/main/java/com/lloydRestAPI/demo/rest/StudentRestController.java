package com.lloydRestAPI.demo.rest;


import com.lloydRestAPI.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    //define @PostConstruct to load the student data... only once
    @PostConstruct
    public void loadData() {

        theStudents = new ArrayList<>();

        theStudents.add(new Student("Lloyd", "Estrada"));
        theStudents.add(new Student("John", "Doe"));
        theStudents.add(new Student("Spongebob", "Squarepants"));
    }

    //define endpoint for '/students' - return list of students
    @GetMapping("/students")
    public List<Student> getStudents(){

        return theStudents;
    }

    //define endpoint (/students/{studentId} - return the student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        //check the studentId
        if( (studentId >= theStudents.size()) || (studentId<0)){

            throw new StudentNotFoundException("Student id not found: " + studentId);
        }

        //just index into the list
        return theStudents.get(studentId);
    }

    //add an exception handler using annotations (@ExceptionHandler)
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){

        //create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
