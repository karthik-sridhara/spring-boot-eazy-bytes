package com.eazybytes.jobportal.contact.controller;

import com.eazybytes.jobportal.contact.service.IContactService;
import com.eazybytes.jobportal.dto.ContactRequestDto;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
//@Tag(name = "Contacts", description = "Operations for submitting and validating contact requests")
public class ContactController {

    private final IContactService contactService;

    @PostMapping(version = "1.0")
//    @Operation(summary = "Create a contact request", description = "Stores a new contact request submitted by a user")
    public ResponseEntity<String> saveContactMsg(@Valid @RequestBody ContactRequestDto contactRequestDto) {
        contactService.saveContact(contactRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Request processed successfully");
    }

    @GetMapping(version = "1.0")
//    @Operation(summary = "Validate contact id", description = "Sample endpoint showing request parameter validation in Swagger")
    public ResponseEntity<String> getContactMsg(
//            @Parameter(description = "Contact identifier", example = "AB123")
            @Valid @NotBlank(message = "Value can't be blank")
            @Size(min = 2, max = 10, message = "Id length should be between 2 and 10")
            @RequestParam String id) {
        return ResponseEntity.ok("Contact Message :"+id);
    }
}
