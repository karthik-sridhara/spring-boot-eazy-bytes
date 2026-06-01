package com.eazybytes.jobportal.domainJob.service;

import com.eazybytes.jobportal.dto.JobDto;

import java.util.List;

public interface JobService {
    List<JobDto> getJobsForEmployer(String email);
    JobDto createJob(JobDto jobDto, String email);
    JobDto updateJobStatus(Long jobId, String email, String status);
}
