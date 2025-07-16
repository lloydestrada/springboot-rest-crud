package com.lloydRestAPI.demo.rest;


import com.lloydRestAPI.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        //just index into the list
        return theStudents.get(studentId);
    }
}
