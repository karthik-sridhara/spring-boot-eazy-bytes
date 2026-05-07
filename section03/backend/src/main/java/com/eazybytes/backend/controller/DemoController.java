package com.eazybytes.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    //@GetMapping(path={"/","/home"})
    //@RequestMapping(path={"/","/home"}) // It can handle any HTTP method
    @RequestMapping(path = {"/","/home"},method = {RequestMethod.GET,RequestMethod.POST}) // It can handle only GET and POST HTTP method
    public String welcome() {
        return "Welcome to Eazybytes Backend!";
    }
}
