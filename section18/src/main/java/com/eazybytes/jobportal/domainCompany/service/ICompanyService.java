package com.eazybytes.jobportal.domainCompany.service;

import com.eazybytes.jobportal.dto.CompanyDto;

import java.util.List;

public interface ICompanyService {

    List<CompanyDto> getAllCompanies();

    CompanyDto createCompany(CompanyDto companyDto);

    List<CompanyDto> getAllCompaniesWithoutJobs();

    void updateCompany(CompanyDto companyDto, Long id);

    void deleteCompany(Long id);
}
