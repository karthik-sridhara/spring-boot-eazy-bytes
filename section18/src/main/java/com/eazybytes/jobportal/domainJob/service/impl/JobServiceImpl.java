package com.eazybytes.jobportal.domainJob.service.impl;

import com.eazybytes.jobportal.domainJob.service.JobService;
import com.eazybytes.jobportal.dto.JobDto;
import com.eazybytes.jobportal.entity.Company;
import com.eazybytes.jobportal.entity.Job;
import com.eazybytes.jobportal.entity.JobPortalUser;
import com.eazybytes.jobportal.exception.BusinessException;
import com.eazybytes.jobportal.repository.JobPortalUserRepository;
import com.eazybytes.jobportal.repository.JobRepository;
import com.eazybytes.jobportal.utility.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobPortalUserRepository  jobPortalUserRepository;
    private final JobRepository   jobRepository;

    @Override
    @Transactional
    public JobDto createJob(JobDto jobDto, String email) {
        JobPortalUser employer = getJobPortalUser(email);
        Company company = getEmployerCompany(employer);
        Job job = Mapper.map(jobDto);
        job.setApplicationsCount(0);
        job.setPostedDate(Instant.now());
        job.setStatus("DRAFT");
        job.setCompany(company);
        jobRepository.save(job);
        return Mapper.map(job);
    }

    @Override
    @Transactional
    public JobDto updateJobStatus(Long jobId, String email, String status) {
        if(status == null || (!status.equals("DRAFT") && !status.equals("ACTIVE") && !status.equals("CLOSE"))){
            throw new BusinessException(
                    "INVALID_STATUS",
                    "Invalid status: "+status,
                    HttpStatus.BAD_REQUEST
            );
        }
        JobPortalUser employer = getJobPortalUser(email);
        Company company = getEmployerCompany(employer);
        List<Job> jobs = getEmployerJobs(company);

        for (Job job : jobs) {
            if(Objects.equals(job.getId(), jobId)){
                job.setStatus(status);
                return Mapper.map(job);
            }
        }

        throw new BusinessException(
                "JOB_NOT_FOUND",
                "No job found with id: "+jobId+" for employer with email: "+email,
                HttpStatus.NOT_FOUND
        );
    }

    @Override
    public List<JobDto> getJobsForEmployer(String email) {
        JobPortalUser employer = getJobPortalUser(email);
        Company company = getEmployerCompany(employer);
        List<Job> jobs = getEmployerJobs(company);
        return jobs.stream().map(Mapper::map).toList();
    }

    private JobPortalUser getJobPortalUser(String email){
        return jobPortalUserRepository.findJobPortalUserByEmail(email)
            .orElseThrow(()->new BusinessException(
                            "NO_USER_FOUND",
                            "No user found with email: "+email,
                            HttpStatus.INTERNAL_SERVER_ERROR
                    )
            );
    }

    private Company getEmployerCompany(JobPortalUser employer){
        Company company = employer.getCompany();
        if(company == null){
            throw new BusinessException(
                    "NO_COMPANY_ASSOCIATED",
                    "Employer with email: "+employer.getEmail()+" is not associated with any company",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return company;
    }

    private List<Job> getEmployerJobs(Company company){
        List<Job> jobs = company.getJobs();
        if(jobs == null){
            throw new BusinessException(
                    "NO_JOBS_FOUND",
                    "No jobs found for employer with email: "+company.getName(),
                    HttpStatus.NOT_FOUND
            );
        }
        return jobs;
    }
}
