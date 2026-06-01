package com.eazybytes.jobportal.utility;


import com.eazybytes.jobportal.constants.ApplicationConstants;
import com.eazybytes.jobportal.dto.CompanyDto;
import com.eazybytes.jobportal.dto.JobDto;
import com.eazybytes.jobportal.dto.UserDto;
import com.eazybytes.jobportal.entity.Company;
import com.eazybytes.jobportal.entity.Job;
import com.eazybytes.jobportal.entity.JobPortalUser;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {


    public static UserDto map(JobPortalUser jobPortalUser){
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

    public static JobDto map(Job job) {
        return new JobDto(
                job.getId(),
                job.getTitle(),
                job.getCompany().getId(),
                job.getCompany().getName(),
                job.getCompany().getLogo(),
                job.getLocation(),
                job.getWorkType(),
                job.getJobType(),
                job.getCategory(),
                job.getExperienceLevel(),
                job.getSalaryMin(),
                job.getSalaryMax(),
                job.getSalaryCurrency(),
                job.getSalaryPeriod(),
                job.getDescription(),
                job.getRequirements(),
                job.getBenefits(),
                job.getPostedDate(),
                job.getApplicationDeadline(),
                job.getApplicationsCount(),
                job.getFeatured(),
                job.getUrgent(),
                job.getRemote(),
                job.getStatus()
        );
    }

    public static Job map(JobDto jobDto) {
        Job job =  new Job();
        job.setId(jobDto.id());
        job.setTitle(jobDto.title());
        job.setLocation(jobDto.location());
        job.setWorkType(jobDto.workType());
        job.setJobType(jobDto.jobType());
        job.setCategory(jobDto.category());
        job.setExperienceLevel(jobDto.experienceLevel());
        job.setSalaryMin(jobDto.salaryMin());
        job.setSalaryMax(jobDto.salaryMax());
        job.setSalaryCurrency(jobDto.salaryCurrency());
        job.setSalaryPeriod(jobDto.salaryPeriod());
        job.setDescription(jobDto.description());
        job.setRequirements(jobDto.requirements());
        job.setBenefits(jobDto.benefits());
        job.setApplicationDeadline(jobDto.applicationDeadline());
        job.setApplicationsCount(jobDto.applicationsCount());
        job.setFeatured(jobDto.featured());
        job.setUrgent(jobDto.urgent());
        job.setRemote(jobDto.remote());
        job.setStatus(ApplicationConstants.ACTIVE_JOB_APPLICATION_STATUS);
        return job;
    }

    private static CompanyDto map(Company company,boolean withJob) {
        List<JobDto> jobs = null;
        if (withJob) {
            jobs = company.getJobs().stream()
                    .map(Mapper::map)
                    .toList();
        }
        return new CompanyDto(company.getId(), company.getName(), company.getLogo(),
                company.getIndustry(), company.getSize(), company.getRating(),
                company.getLocations(), company.getFounded(), company.getDescription(),
                company.getEmployees(), company.getWebsite(), company.getCreatedAt(),jobs);
    }

    public static CompanyDto map(Company company) {
        return map(company,true);
    }

    public static CompanyDto mapWithoutJobs(Company company) {
        return map(company,false);
    }


}
