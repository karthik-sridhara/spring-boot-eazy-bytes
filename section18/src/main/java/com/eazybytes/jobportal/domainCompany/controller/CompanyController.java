package com.eazybytes.jobportal.domainCompany.controller;

import com.eazybytes.jobportal.dto.CompanyDto;
import com.eazybytes.jobportal.domainCompany.service.ICompanyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final ICompanyService companyService;

    @GetMapping(path = "/public", version = "1.0")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<CompanyDto> companyList = companyService.getAllCompanies();
        return ResponseEntity.ok().body(companyList);
    }

    @PostMapping(path = "/admin", version = "1.0")
    public ResponseEntity<CompanyDto> createCompany(@RequestBody @Valid CompanyDto companyDto) {
        return ResponseEntity.ok().body(companyService.createCompany(companyDto));
    }

    @GetMapping(path = "/admin", version = "1.0")
    public ResponseEntity<List<CompanyDto>> getAllCompaniesByAdmin() {
        return ResponseEntity.ok().body(companyService.getAllCompaniesWithoutJobs());
    }

    @PutMapping(path = "/{id}/admin",  version = "1.0")
    public ResponseEntity<String> updateCompany(
            @PathVariable
            @NotBlank(message = "Company ID must not be blank")
            String id,
            @RequestBody @Valid CompanyDto companyDto
    ) {
        companyService.updateCompany(companyDto,Long.valueOf(id));
        return ResponseEntity.ok().body("Updated company with id: " + id);
    }

    @DeleteMapping(path = "/{id}/admin", version = "1.0")
    public ResponseEntity<String> deleteCompany(
        @PathVariable
        @NotBlank(message = "Company ID must not be blank")
        String id
    ) {
        companyService.deleteCompany(Long.valueOf(id));
        return ResponseEntity.ok().body("Deleted company with id: " + id);
    }
}
