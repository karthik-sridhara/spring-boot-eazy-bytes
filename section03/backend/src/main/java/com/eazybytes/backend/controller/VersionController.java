package com.eazybytes.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/version/{v}")
@RequestMapping("/version")
public class VersionController {

    @GetMapping(version = "1.0")
    public ResponseEntity<String> getVersionV1() {
        return ResponseEntity.ok("Version 1");
    }


    @GetMapping(version = "2.0+")
    public ResponseEntity<String> getVersionV2() {
        return ResponseEntity.ok("Version 2");
    }
}
