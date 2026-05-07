package com.eazybytes.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/legacy/version")
public class LegacyVersionController {

    @GetMapping(path={"/","/v1",""})
    public ResponseEntity<String> getVersion1ByPath(){
        return ResponseEntity.ok("Fetching Info v1 from path");
    }

    @GetMapping("/v2")
    public ResponseEntity<String> getVersion2ByPath() {
         return ResponseEntity.ok("Fetching Info v2 from path");
    }

    @GetMapping(path={"","/"},params = "version=1")
    public ResponseEntity<String> getVersion1ByParams(){
        return ResponseEntity.ok("Fetching Info v1 from query params");
    }

    @GetMapping(path={"","/"},params = "version=2")
    public ResponseEntity<String> getVersion2ByParams() {
        return ResponseEntity.ok("Fetching Info v2 from query params");
    }

    @GetMapping(path={"","/"},headers = "X-API-HEADER=1")
    public ResponseEntity<String> getVersion1ByHeaders(){
        return ResponseEntity.ok("Fetching Info v1 from headers");
    }

    @GetMapping(path={"","/"},headers = "X-API-HEADER=2")
    public ResponseEntity<String> getVersion2ByHeaders() {
        return ResponseEntity.ok("Fetching Info v2 from headers");
    }

    @GetMapping(produces = "application/vnd.eazybytes.v1+json")
    public ResponseEntity<String> getVersion1ByMedia(){
        return ResponseEntity.ok("Fetching Info v1 from Media");
    }

    @GetMapping(produces = "application/vnd.eazybytes.v2+json")
    public ResponseEntity<String> getVersion2ByMedia() {
        return ResponseEntity.ok("Fetching Info v2 from Media");
    }
}
