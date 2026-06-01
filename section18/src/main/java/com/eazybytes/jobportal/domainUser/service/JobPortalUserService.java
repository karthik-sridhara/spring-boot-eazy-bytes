package com.eazybytes.jobportal.domainUser.service;

import com.eazybytes.jobportal.dto.UserDto;

import java.util.List;


public interface JobPortalUserService {
    UserDto searchUserByEmail(String email);
    UserDto updateUserRole(Long userId);
    List<UserDto> getAllUsers();
    UserDto assignCompany(Long userId, Long companyId);
}
