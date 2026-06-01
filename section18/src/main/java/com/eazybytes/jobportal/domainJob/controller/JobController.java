package com.eazybytes.jobportal.domainJob.controller;

import com.eazybytes.jobportal.domainJob.service.JobService;
import com.eazybytes.jobportal.dto.JobDto;
import com.eazybytes.jobportal.dto.LoggedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping("/employer")
    public ResponseEntity<List<JobDto>> getJobsForEmployer(
        @AuthenticationPrincipal(errorOnInvalidType = true) LoggedInUser loggedInUser
    ) {
        List<JobDto> jobs = jobService.getJobsForEmployer(loggedInUser.email());
        return ResponseEntity.ok(jobs);
    }

    @PostMapping("/employer")
    public ResponseEntity<JobDto> createJob(
        @RequestBody JobDto job,
        @AuthenticationPrincipal(errorOnInvalidType = true) LoggedInUser loggedInUser
    ) {
        JobDto createdJob = jobService.createJob(job, loggedInUser.email());
        return ResponseEntity.ok(createdJob);
    }

    @PatchMapping("/{jobId}/status/employer")
    public ResponseEntity<JobDto> updateJobStatus(
        @AuthenticationPrincipal(errorOnInvalidType = true) LoggedInUser loggedInUser,
        @PathVariable Long jobId,
        @RequestParam String status
    ) {
        JobDto updatedJob = jobService.updateJobStatus(jobId, loggedInUser.email(), status);
        return ResponseEntity.ok(updatedJob);
    }

}
