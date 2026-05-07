package com.eazybytes.jobportal.aspect;

import com.eazybytes.jobportal.dto.RegisterRequestDto;
import com.eazybytes.jobportal.entity.JobPortalUser;
import com.eazybytes.jobportal.exception.RegisterValidationException;
import com.eazybytes.jobportal.repository.JobPortalUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationValidationAspect {

    private final CompromisedPasswordChecker compromisedPasswordChecker;
    private final JobPortalUserRepository jobPortalUserRepository;


    @Before("""
        execution(* com.eazybytes.jobportal.auth.AuthController.registerUser(..))
    """)
    public void validateRequestBody(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        RegisterRequestDto requestDto = (RegisterRequestDto) args[0];
        Map<String,String> errors = new HashMap<>();
        CompromisedPasswordDecision decision = compromisedPasswordChecker
                .check(requestDto.password());
        if (decision.isCompromised()) {
            log.error("Registration attempt with compromised password: {}", requestDto.password());
            errors.put("password", "Please choose a different password.");
            throw new RegisterValidationException(
                "Password is compromised. Please choose a different password.",
                errors
            );
        }

        Optional<JobPortalUser> existingUser = jobPortalUserRepository.readUserByEmailOrMobileNumber
                (requestDto.email(), requestDto.mobileNumber());
        if (existingUser.isPresent()) {
            JobPortalUser jobPortalUser = existingUser.get();
            if (jobPortalUser.getEmail().equalsIgnoreCase(requestDto.email())) {
                errors.put("email", "Email is already registered");
            }
            if (jobPortalUser.getMobileNumber().equals(requestDto.mobileNumber())) {
                errors.put("mobileNumber", "Mobile number is already registered");
            }
            log.error("Either email or  mobileNumber already registered. {}",errors);
            throw new RegisterValidationException(
                    "Either mobileNumber or email already registered.",
                    errors
            );
        }
        log.info("Registration validation passed" );

    }
}
