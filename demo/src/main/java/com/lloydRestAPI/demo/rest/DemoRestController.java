package com.lloydRestAPI.demo.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoRestController {

    //add test
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello Lloyd!\n Kumusta Ka?";
    }

    @GetMapping("/another")
    public String saySchool(){

        return "graduated at New Era University";
    }
}
