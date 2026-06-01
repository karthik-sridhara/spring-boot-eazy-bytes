package com.eazybyte.jobportal.company.service;

import com.eazybyte.jobportal.company.entity.Company;
import com.eazybyte.jobportal.company.repository.CompanyRepo;
import com.eazybyte.jobportal.dto.CompanyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepo companyRepo;

//    @Autowired
//    public CompanyService(CompanyRepo companyRepo){
//        this.companyRepo = companyRepo;
//    }

    public List<CompanyDTO> getAllCompanies(){
        List<Company> companies = companyRepo.findAll();
        return companies.stream()
                .map(this::transformToDTO)
                .toList();
    }

    private CompanyDTO transformToDTO(Company company) {
        return new CompanyDTO(
            company.getId(),
            company.getName(),
            company.getLogo(),
            company.getIndustry(),
            company.getSize(),
            company.getRating(),
            company.getLocations(),
            company.getFounded(),
            company.getDescription(),
            company.getEmployees(),
            company.getWebsite(),
            company.getCreatedAt()
        );
    }
}
