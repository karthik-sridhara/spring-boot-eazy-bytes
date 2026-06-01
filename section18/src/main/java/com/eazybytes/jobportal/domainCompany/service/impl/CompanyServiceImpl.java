package com.eazybytes.jobportal.domainCompany.service.impl;

import com.eazybytes.jobportal.domainCompany.service.ICompanyService;
import com.eazybytes.jobportal.dto.CompanyDto;
import com.eazybytes.jobportal.entity.Company;
import com.eazybytes.jobportal.exception.BusinessException;
import com.eazybytes.jobportal.repository.CompanyRepository;
import com.eazybytes.jobportal.utility.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
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
        List<Company> companyList = companyRepository.findAllByJobStatus("ACTIVE");
        return companyList.stream().map(Mapper::map).collect(Collectors.toList());
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
        return Mapper.mapWithoutJobs(savedCompany);
    }

    @Override
    @Cacheable("companies")
    public List<CompanyDto> getAllCompaniesWithoutJobs() {
        List<Company> companyList = companyRepository.findAll();

        return companyList.stream()
                .map(Mapper::mapWithoutJobs)
                .toList();
    }

    @Transactional
    @Override
    public void updateCompany(CompanyDto companyDto, Long id) {
        int updatedRows = companyRepository.updateCompanyDetails(
                id,
                companyDto.name(),
                companyDto.logo(),
                companyDto.industry(),
                companyDto.size(),
                companyDto.rating(),
                companyDto.locations(),
                companyDto.founded(),
                companyDto.description(),
                companyDto.employees(),
                companyDto.website()
        );
        if (updatedRows != 1) {
            throw new BusinessException("COMPANY_NOT_FOUND", "No company found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

}
