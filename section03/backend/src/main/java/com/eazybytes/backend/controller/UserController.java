package com.eazybytes.backend.controller;

import com.eazybytes.backend.dto.UserDTO;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping(path={"/{userId}/posts/{postId}","/{userId}/posts"})
    public ResponseEntity<String> getUserPostByPostID(@PathVariable Long userId, @PathVariable(required = false) Long postId) {
        StringBuilder response = new StringBuilder("User ID: " + userId );
        if(postId != null){
            response.append(", Post ID: ").append(postId);
        }
        //return response.toString();
        return ResponseEntity.ok(response.toString());
    }

    @GetMapping(path={"/{userId}/orders/{orderId}"})
    public ResponseEntity<String> getUserOrderByOrderID(@PathVariable("userId") Long customerID, @PathVariable Long orderId) {
        StringBuilder response = new StringBuilder("User ID: " + customerID );
        if(orderId != null){
            response.append(", Order ID: ").append(orderId);
        }
//        return response.toString();
        return ResponseEntity.ok().body(response.toString());
    }

    @GetMapping(path={"/{userId}/address/{addressId}"})
    public String getUserAddressByAddressID(@PathVariable Map<String, String> pathVariables) {
        return String.format("User ID: %s, Address ID: %s", pathVariables.get("userId"), pathVariables.get("addressId"));
    }

    @GetMapping("/search")
    public String searchUser(
            @RequestParam(required = false) String name,
            @RequestParam(required = false,defaultValue = "Male") String gender
    ){
        StringBuilder param = new StringBuilder();
        if(name != null && !name.isBlank()){
            param.append(" Name: ").append(name);
        }
        if(gender != null && !gender.isBlank()){
            if(!param.isEmpty()){
                param.append(" And");
            }
            param.append(" Gender: ").append(gender);
        }
        return "Search User BY"+param.toString();
    }

    @GetMapping("/search/map")
    public String searchUserWithMap(
            @RequestParam Map<String, String> queryParams
    ){

        StringBuilder param = new StringBuilder();

        for(String key: queryParams.keySet()){
            if(!param.isEmpty()){
                param.append(" And");
            }
            param.append(" "+key).append(": ").append(queryParams.get(key));
        }
        return "Search User BY"+param.toString();
    }

    @GetMapping("/header")
    public String getHeaders(
            @RequestHeader(name="content-type",required = false) String contentType,
            @RequestHeader("User-Agent") String userAgent
    ){
        return String.format("Content-Type: %s, User-Agent: %s", contentType, userAgent);
    }

    @GetMapping("/header/map")
    public String getHeadersMap(
            @RequestHeader Map<String, String> headers
    ){
        StringBuilder receivedHeaders = new StringBuilder();

        for(String key: headers.keySet()){
            if(!receivedHeaders.isEmpty()){
                receivedHeaders.append(" And");
            }
            receivedHeaders.append(" "+key).append(": ").append(headers.get(key));
        }
        return receivedHeaders.toString();
    }

    @GetMapping("/header/http")
    public String getHeadersByHTTPHeader(
            @RequestHeader HttpHeaders headers
    ){
        StringBuilder receivedHeaders = new StringBuilder();

        for(String key: headers.headerNames()){
            if(!receivedHeaders.isEmpty()){
                receivedHeaders.append(" And");
            }
            receivedHeaders.append(" "+key).append(": ").append(headers.get(key));
        }
        return receivedHeaders.toString();
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO){
        // return "User Created Successfully!\nUser: "+userDTO.toString();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("custom-header","example-value")
                .body("User Created Successfully!\nUser: "+userDTO.toString());
    }

    @PostMapping("/request-entity")
    public String createUserWithRequestEntity(RequestEntity<UserDTO> requestEntity){
        UserDTO userDTO = requestEntity.getBody();
        HttpHeaders headers = requestEntity.getHeaders();
        String queryParm = requestEntity.getUrl().getQuery();
        String path = requestEntity.getUrl().getPath();
        return "User Created Successfully!\nUser: "+userDTO.toString()
                +"\nHeaders: "+headers.toString()
                +"\nQueryParam: "+queryParm
                +"\nPath: "+path;
    }
}
