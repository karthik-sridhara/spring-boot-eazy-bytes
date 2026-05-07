package com.eazybytes.jobportal.company.service.impl;

import com.eazybytes.jobportal.company.service.ICompanyService;
import com.eazybytes.jobportal.dto.CompanyDto;
import com.eazybytes.jobportal.dto.JobDto;
import com.eazybytes.jobportal.entity.Company;
import com.eazybytes.jobportal.entity.Job;
import com.eazybytes.jobportal.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> companyList = companyRepository.findAllCompaniesByJobStatusNative("ACTIVE");
        return companyList.stream().map(this::transformCompanyToDto).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = new Company();
        company.setName(companyDto.name());
        company.setLogo(companyDto.logo());
        company.setIndustry(companyDto.industry());
        company.setSize(companyDto.size());
        company.setRating(companyDto.rating());
        company.setLocations(companyDto.locations());
        company.setFounded(companyDto.founded());
        company.setDescription(companyDto.description());
        company.setEmployees(companyDto.employees());
        company.setWebsite(companyDto.website());

        Company savedCompany = companyRepository.save(company);
        return transformCompanyToDto(savedCompany);
    }

    @Override
    public List<CompanyDto> getAllCompaniesWithoutJobs() {
        List<Company> companyList = companyRepository.findAll();

        return companyList.stream()
                .map(this::transformCompanyToDtoWithoutJobs)
                .collect(Collectors.toList());
    }

    private CompanyDto transformCompanyToDtoWithoutJobs(Company company) {
        return new CompanyDto(company.getId(), company.getName(), company.getLogo(),
                company.getIndustry(), company.getSize(), company.getRating(),
                company.getLocations(), company.getFounded(), company.getDescription(),
                company.getEmployees(), company.getWebsite(), company.getCreatedAt(),null);
    }

    private CompanyDto transformCompanyToDto(Company company) {
        List<JobDto> jobDtos = company.getJobs().stream()
                .map(this::transformJobToDto)
                .collect(Collectors.toList());
        return new CompanyDto(company.getId(), company.getName(), company.getLogo(),
                company.getIndustry(), company.getSize(), company.getRating(),
                company.getLocations(), company.getFounded(), company.getDescription(),
                company.getEmployees(), company.getWebsite(), company.getCreatedAt(),jobDtos);
    }

    private JobDto transformJobToDto(Job job) {
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

}
