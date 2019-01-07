package me.lenycer.jpa.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by a1100440 on 07/01/2019.
 */
@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompany(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

    @Transactional
    public void update(Company company) {
        Company findCompany = companyRepository.findById(company.getId()).orElseThrow(() -> new RuntimeException("not found"));
        findCompany.setLocation("updateLocation");
    }

    @Transactional
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
