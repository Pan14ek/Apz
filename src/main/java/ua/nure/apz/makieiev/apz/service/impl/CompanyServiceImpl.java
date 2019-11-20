package ua.nure.apz.makieiev.apz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.apz.makieiev.apz.model.Company;
import ua.nure.apz.makieiev.apz.repository.CompanyRepository;
import ua.nure.apz.makieiev.apz.service.CompanyService;

import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company add(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company update(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Optional<Company> findById(long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Optional<Company> getByTitle(String title) {
        return companyRepository.findByTitle(title);
    }

    @Override
    public boolean removeById(long id) {
        companyRepository.deleteById(id);
        return true;
    }

}