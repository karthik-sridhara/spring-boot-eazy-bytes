package com.eazybytes.jobportal.dto;

public record LoggedInUser(
   String name,
   String email,
   String mobileNumber,
   String role
) {}
