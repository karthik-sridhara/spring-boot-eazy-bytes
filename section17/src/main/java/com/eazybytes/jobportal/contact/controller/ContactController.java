package com.eazybytes.jobportal.contact.controller;

import com.eazybytes.jobportal.contact.service.IContactService;
import com.eazybytes.jobportal.dto.ContactRequestDto;
import com.eazybytes.jobportal.dto.ContactResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final IContactService contactService;

    @PostMapping(path = "/public", version = "1.0")
    public ResponseEntity<String> saveContactMsg(@RequestBody @Valid ContactRequestDto contactRequestDto) {
        boolean isSaved =  contactService.saveContact(contactRequestDto);
        if (isSaved) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Request processed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Request processing failed");
        }
    }

    @GetMapping
    public ResponseEntity<String> getContactMsg() {
        return ResponseEntity.ok("This is a placeholder for fetching contact messages");
    }

    @GetMapping("/admin")
    public ResponseEntity<List<ContactResponseDto>> getContactMessages() {
        return ResponseEntity.ok(contactService.getContactMessages());
    }

    @GetMapping("/sort/admin")
    public ResponseEntity<List<ContactResponseDto>> getContactMessagesWithSort(
            @RequestParam(value = "sort",defaultValue = "createdAt", required = false) String sortBy,
            @RequestParam(value = "order",defaultValue = "desc", required = false) String sortOrder
    ) {
        return ResponseEntity.ok(contactService.getContactMessagesWithSort(sortBy,sortOrder));
    }

    @GetMapping("/page/admin")
    public ResponseEntity<Page<ContactResponseDto>> getContactMessagesWithPage(
            @RequestParam(value = "page",defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size",defaultValue = "10", required = false) Integer size,
            @RequestParam(value = "sort",defaultValue = "createdAt", required = false) String sortBy,
            @RequestParam(value = "order",defaultValue = "desc", required = false) String sortOrder
    ) {
        return ResponseEntity.ok(contactService.getContactMessagesWithPage(page,size,sortBy,sortOrder));
    }

    @PatchMapping("/{id}/status/admin")
    public ResponseEntity<String> closeContactMessage(
            @PathVariable("id") Long id
    ) {
        contactService.closeContactMsg(id);
        return ResponseEntity.ok("Contact message status updated successfully");
    }

}
