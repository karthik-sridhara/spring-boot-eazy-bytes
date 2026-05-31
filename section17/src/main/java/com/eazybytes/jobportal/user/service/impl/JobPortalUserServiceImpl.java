package com.eazybytes.jobportal.user.service.impl;

import com.eazybytes.jobportal.constants.ApplicationConstants;
import com.eazybytes.jobportal.dto.UserDto;
import com.eazybytes.jobportal.entity.Company;
import com.eazybytes.jobportal.entity.JobPortalUser;
import com.eazybytes.jobportal.entity.Role;
import com.eazybytes.jobportal.exception.BusinessException;
import com.eazybytes.jobportal.repository.CompanyRepository;
import com.eazybytes.jobportal.repository.JobPortalUserRepository;
import com.eazybytes.jobportal.repository.RoleRepository;
import com.eazybytes.jobportal.user.service.JobPortalUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobPortalUserServiceImpl implements JobPortalUserService {

    private final JobPortalUserRepository jobPortalUserRepository;
    private final RoleRepository roleRepository;
    private final CompanyRepository companyRepository;

    public UserDto searchUserByEmail(String email){
        JobPortalUser user = jobPortalUserRepository.findJobPortalUserByEmail(email)
            .orElseThrow(()->new BusinessException(
                    "NO_USER_FOUND",
                    "No user found with email: "+email,
                    HttpStatus.NOT_FOUND
                )
            );
        return transformJobPortalUserToUserDto(user);
    }


    @Override
    @Transactional
    public UserDto updateUserRole(Long userId) {
        JobPortalUser user = jobPortalUserRepository.findById(userId)
            .orElseThrow(()->new BusinessException(
                    "NO_USER_FOUND",
                    "No user found with id: "+userId,
                    HttpStatus.NOT_FOUND
                )
            );
        if(user.getRole() != null && ApplicationConstants.ROLE_ADMIN.equals(user.getRole().getName())){
            throw new BusinessException(
                "ILLEGAL_OPERATION",
                "User with id: "+userId+" is an admin",
                HttpStatus.BAD_REQUEST
            );
        }
        if(user.getRole() != null && user.getRole().getName().equals(ApplicationConstants.ROLE_EMPLOYER)){
            return transformJobPortalUserToUserDto(user);
        }
        Role employeeRole = roleRepository.findRoleByName(ApplicationConstants.ROLE_EMPLOYER)
            .orElseThrow(()->new BusinessException(
                    "NO_ROLE_FOUND",
                    "No role found with name: "+ApplicationConstants.ROLE_EMPLOYER,
                    HttpStatus.NOT_FOUND
                )
            );
        user.setRole(employeeRole);

        return transformJobPortalUserToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return jobPortalUserRepository.findAll().stream()
            .map(this::transformJobPortalUserToUserDto)
            .toList();
    }

    @Override
    @Transactional
    public UserDto assignCompany(Long userId, Long companyId) {
        JobPortalUser user = jobPortalUserRepository.findById(userId)
            .orElseThrow(()->new BusinessException(
                    "NO_USER_FOUND",
                    "No user found with id: "+userId,
                    HttpStatus.NOT_FOUND
                )
            );
        if(user.getRole() == null || !ApplicationConstants.ROLE_EMPLOYER.equals(user.getRole().getName())){
            throw new BusinessException(
                "ILLEGAL_OPERATION",
                "User must be an Employee",
                HttpStatus.BAD_REQUEST
            );
        }
        Company company = companyRepository.findById(companyId)
            .orElseThrow(()->new BusinessException(
                    "NO_COMPANY_FOUND",
                    "No company found with id: "+companyId,
                    HttpStatus.NOT_FOUND
                )
            );
        user.setCompany(company);
        return transformJobPortalUserToUserDto(user);
    }

    private UserDto transformJobPortalUserToUserDto(JobPortalUser jobPortalUser){
        UserDto userDto = new UserDto();
        userDto.setEmail(jobPortalUser.getEmail());
        userDto.setName(jobPortalUser.getName());
        userDto.setUserId(jobPortalUser.getId());
        userDto.setRole(jobPortalUser.getRole().getName());
        if(jobPortalUser.getCompany()!=null){
            userDto.setCompanyId(jobPortalUser.getCompany().getId());
            userDto.setCompanyName(jobPortalUser.getCompany().getName());
        }
        userDto.setMobileNumber(jobPortalUser.getMobileNumber());
        userDto.setCreatedAt(jobPortalUser.getCreatedAt());
        return userDto;
    }


}
