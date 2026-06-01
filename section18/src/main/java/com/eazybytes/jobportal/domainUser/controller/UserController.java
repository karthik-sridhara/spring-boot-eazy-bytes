package com.eazybytes.jobportal.domainUser.controller;

import com.eazybytes.jobportal.dto.UserDto;
import com.eazybytes.jobportal.domainUser.service.JobPortalUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final JobPortalUserService jobPortalUserService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(jobPortalUserService.getAllUsers());
    }

    @GetMapping("/search")
    public ResponseEntity<UserDto> searchJobPortalUserByEmail(String email) {
        return ResponseEntity.ok(jobPortalUserService.searchUserByEmail(email));
    }

    @PatchMapping("/{userId}/role/employer/admin")
    public ResponseEntity<UserDto> updateUserRole(@PathVariable Long userId) {
        return ResponseEntity.ok(jobPortalUserService.updateUserRole(userId));
    }

    @PatchMapping("/{userId}/company/{companyId}/admin")
    public ResponseEntity<UserDto> assignCompany(
        @PathVariable Long userId,
        @PathVariable Long companyId
    ) {
        return ResponseEntity.ok(jobPortalUserService.assignCompany(userId, companyId));
    }
}
